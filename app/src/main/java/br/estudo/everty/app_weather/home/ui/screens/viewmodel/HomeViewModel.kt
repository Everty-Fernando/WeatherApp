package br.estudo.everty.app_weather.home.ui.screens.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.estudo.everty.app_weather.home.domain.usecase.GetMeteorologicalDataUseCase
import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState
import br.estudo.everty.app_weather.home.ui.screens.state.HomeStateUI
import br.estudo.everty.app_weather.utils.LocationUtils
import br.estudo.everty.app_weather.utils.LocationUtils.requestPermissionLocale
import br.estudo.everty.app_weather.utils.extensions.isConnected
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMeteorologicalDataUseCase: GetMeteorologicalDataUseCase,
    private val context: Context
): ViewModel() {

    var homeStateUI: HomeStateUI by mutableStateOf(HomeStateUI())
        private set



    var cityName by mutableStateOf("")
        private set

    var latitude: Double = 0.0
        private set

    var longitude: Double = 0.0
        private set

    fun checkLocationEnable(activity: Activity) {
        val locationIsEnabled = LocationUtils.isLocationEnabled(activity)
        if (!locationIsEnabled) {
           setErrorState(errorState = ErrorState.LOCATION_DISABLED)
        } else {
            cleanErrorState()
        }
    }
    fun setLocalization(lat: Double, long: Double) {
        latitude = lat
        longitude = long
    }

    fun getMeteorologicalData() = viewModelScope.launch {
        if (!context.isConnected()) {
            delay(1000)
            setErrorState(ErrorState.NETWORK_DISABLED)
        } else {
            getMeteorologicalDataUseCase(latitude, longitude).onStart {
                setLoading(true)
            }.catch {
                it.printStackTrace()
                setErrorState(ErrorState.ERROR_SERVER)
            }.collect { meteorologicalData ->
                setMeteorologicalData(meteorologicalData)
            }
        }
    }

    fun cleanErrorState() {
        homeStateUI = homeStateUI.copy(errorState = null)
    }

    fun setLoading(isLoading: Boolean) {
        homeStateUI = homeStateUI.copy(isLoading =  isLoading)
    }

    fun setErrorState(errorState: ErrorState) {
        homeStateUI = homeStateUI.copy(errorState = errorState, isLoading = false)
    }

    private fun setMeteorologicalData(meteorologicalDataUI: MeteorologicalDataUI) {
        homeStateUI = homeStateUI.copy(meteorologicalDataResponse = meteorologicalDataUI, isLoading = false)
    }

    fun setCurrentCityName(cityName: String) {
        this.cityName = cityName
    }
}