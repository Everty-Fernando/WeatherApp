package br.estudo.everty.app_weather.home.ui.screens.state

import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.estudo.everty.app_weather.home.domain.model.WeatherTimelineUI
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState

data class HomeStateUI(
    val meteorologicalDataUI: MeteorologicalDataUI? = null,
    val cityName: String = "",
    val isLoading: Boolean = false,
    val isLoadingFilters: Boolean = false,
    val listWeathersFiltered: List<WeatherTimelineUI> = listOf(),
    val locationEnabled: Boolean = false,
    val errorState: ErrorState? = null
)