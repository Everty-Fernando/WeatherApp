package br.estudo.everty.app_weather.home.ui.screens.viewmodel

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.estudo.everty.app_weather.home.domain.usecase.GetMeteorologicalDataUseCase
import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.estudo.everty.app_weather.home.domain.model.WeatherTimelineUI
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState
import br.estudo.everty.app_weather.home.ui.screens.state.HomeStateUI
import br.estudo.everty.app_weather.utils.extensions.isConnected
import br.estudo.everty.app_weather.utils.extensions.runAfter
import br.estudo.everty.app_weather.utils.helpers.LocationHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val application: Application,
    private val getMeteorologicalDataUseCase: GetMeteorologicalDataUseCase,
    private val locationHelper: LocationHelper
): ViewModel() {

    var homeStateUI: HomeStateUI by mutableStateOf(HomeStateUI())
        private set

    var latitude: Double = 0.0
        private set

    var longitude: Double = 0.0
        private set

    /**
     * Checar se a localização está ativada e definir estado de erro, se necessário.
     */
    fun checkLocationEnabled() {
        if (!locationHelper.isLocationEnabled()) setErrorState(ErrorState.LOCATION_DISABLED)
        else cleanErrorState()
    }

    /**
     * Solicitar permissão para localização.
     */
    fun requestPermissionLocale(
        activity: Activity,
        onPermissionGranted: () -> Unit
    ) {
        locationHelper.checkAndRequestPermissionLocale(
            activity = activity,
            onPermissionGranted = onPermissionGranted,
            onPermissionDenied = {
                setErrorState(ErrorState.LOCATION_PERMISSION_DENIED)
            }
        )
    }

    /**
     * Recuperar localização do dispositivo.
     */
    fun getDeviceLocation() {
        locationHelper.getLastKnownLocation(
            onLocationRetrieved = { location ->
                latitude = location.latitude
                longitude = location.longitude
                getCityName()
                checkNetworkAndFetchData()
            },
            onError = {
                setErrorState(ErrorState.ERROR_GET_LOCATION)
            }
        )
    }

    /**
     * Checar se a rede está disponível e buscar dados meteorológicos.
     */
    fun checkNetworkAndFetchData() {
        if (!application.isConnected()) setErrorState(ErrorState.NETWORK_DISABLED)
        else getMeteorologicalData()
    }

    fun onFilterSelected(isDaily: Boolean) {
        val listWeather = getFilteredWeather(isDaily)
        updateFiltersState(listWeather)
    }

    fun cleanErrorState() {
        setHomeStateUI(errorState = null)
    }

    fun setLoading(isLoading: Boolean) {
        setHomeStateUI(isLoading = isLoading)
    }

    /**
     * Obter o nome da cidade com base nas coordenadas.
     */
    private fun getCityName() {
        locationHelper.getCityName(
            latitude = latitude,
            longitude = longitude,
            onCityNameRetrieved = { cityName ->
                setHomeStateUI(cityName = cityName)
            }
        )
    }

    private fun getMeteorologicalData() = viewModelScope.launch {
        getMeteorologicalDataUseCase(latitude, longitude).onStart {
            setLoading(true)
        }.catch {
            setErrorState(ErrorState.ERROR_SERVER)
        }.collect { meteorologicalData ->
            setHomeStateUI(
                meteorologicalDataUI = meteorologicalData,
                isLoading = false
            )
            onFilterSelected(true)
        }
    }

    private fun setHomeStateUI(
        meteorologicalDataUI: MeteorologicalDataUI? = homeStateUI.meteorologicalDataUI,
        cityName: String = homeStateUI.cityName,
        isLoading: Boolean = homeStateUI.isLoading,
        locationEnabled: Boolean = homeStateUI.locationEnabled,
        errorState: ErrorState? = homeStateUI.errorState,
        isLoadingFilters: Boolean = homeStateUI.isLoadingFilters,
        listWeathersFiltered: List<WeatherTimelineUI> = homeStateUI.listWeathersFiltered
    ) {
        homeStateUI = homeStateUI.copy(
            isLoading = isLoading,
            meteorologicalDataUI = meteorologicalDataUI,
            cityName = cityName,
            locationEnabled = locationEnabled,
            errorState = errorState,
            isLoadingFilters = isLoadingFilters,
            listWeathersFiltered = listWeathersFiltered
        )
    }

    private fun setErrorState(errorState: ErrorState) {
        setHomeStateUI(errorState = errorState, isLoading = false)
    }

    private fun getFilteredWeather(isDaily: Boolean): List<WeatherTimelineUI> {
        return if (isDaily) homeStateUI.meteorologicalDataUI?.daily ?: emptyList()
        else homeStateUI.meteorologicalDataUI?.hourly ?: emptyList()
    }

    private fun updateFiltersState(listWeather: List<WeatherTimelineUI>) {
        setHomeStateUI(listWeathersFiltered = listWeather, isLoadingFilters = true)
        runAfter { //Colocando um delay no loading, apenas para experiencia do usuário
            setHomeStateUI(isLoadingFilters = false)
        }
    }
}