package br.com.everty.home.home_ui.ui.viewmodel

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.everty.home.home_domain.model.MeteorologicalDataUI
import br.com.everty.home.home_domain.model.WeatherTimelineUI
import br.com.everty.home.home_domain.usecase.GetMeteorologicalDataUseCase
import br.com.everty.home.home_ui.model.ErrorState
import br.com.everty.home.home_ui.model.FilterType
import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.shared.utils.extensions.isConnected
import br.com.everty.shared.utils.extensions.runAfter
import br.com.everty.shared.utils.helpers.LocationHelper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import br.com.everty.shared.presentation.R

class HomeViewModel(
    private val application: Application,
    private val getMeteorologicalDataUseCase: GetMeteorologicalDataUseCase,
    private val locationHelper:LocationHelper,
    initialState: HomeStateUI = HomeStateUI()
): ViewModel() {

    var homeStateUI: HomeStateUI by mutableStateOf(initialState)
        private set

    var latitude: Double = 0.0
        private set

    var longitude: Double = 0.0
        private set

    /**
     * Checar se a localização está ativada e definir estado de erro, se necessário.
     */
    fun checkLocationEnabled() {
        if (!locationHelper.isLocationEnabled()) setErrorState(ErrorState.LocationDisabled)
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
                setErrorState(ErrorState.LocationPermissionDenied)
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
                setErrorState(ErrorState.LocationGetError)
            }
        )
    }

    /**
     * Checar se a rede está disponível e buscar dados meteorológicos.
     */
    fun checkNetworkAndFetchData() {
        if (!application.isConnected()) setErrorState(ErrorState.NetworkDisabled)
        else getMeteorologicalData()
    }

    fun onFilterSelected(isWeekly: Boolean) {
        setFilterSelected(isWeekly)
        val listWeather = getFilteredWeather(isWeekly)
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
        }.catch { exception ->
            val errorMessage = exception.message ?: application.getString(R.string.unknown_error)
            setErrorState(ErrorState.ServerError(errorMessage))
        }.collect { meteorologicalData ->
            setHomeStateUI(
                meteorologicalDataUI = meteorologicalData,
                isLoading = false
            )
            onFilterSelected(isWeekly = false)
        }
    }

    private fun setErrorState(errorState: ErrorState) {
        setHomeStateUI(errorState = errorState, isLoading = false)
    }

    private fun setFilterSelected(isWeekly: Boolean) {
        setHomeStateUI(filterSelected = if (isWeekly) FilterType.WEEKLY else FilterType.DAILY)
    }

    private fun getFilteredWeather(isWeekly: Boolean): List<WeatherTimelineUI> {
        return if (!isWeekly) homeStateUI.meteorologicalDataUI?.hourly ?: emptyList()
        else homeStateUI.meteorologicalDataUI?.daily ?: emptyList()
    }

    private fun updateFiltersState(listWeather: List<WeatherTimelineUI>) {
        setHomeStateUI(listWeathersFiltered = listWeather, isLoadingFilters = true)
        runAfter { //Colocando um delay no loading, apenas para experiencia do usuário
            setHomeStateUI(isLoadingFilters = false)
        }
    }

    private fun setHomeStateUI(
        meteorologicalDataUI: MeteorologicalDataUI? = homeStateUI.meteorologicalDataUI,
        cityName: String = homeStateUI.cityName,
        filterSelected: FilterType = homeStateUI.filterSelected,
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
            filterSelected = filterSelected,
            locationEnabled = locationEnabled,
            errorState = errorState,
            isLoadingFilters = isLoadingFilters,
            listWeathersFiltered = listWeathersFiltered
        )
    }
}