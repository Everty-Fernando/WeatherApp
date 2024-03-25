package br.estudo.everty.app_weather.home.ui.screens.state

import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState

data class HomeStateUI(
    val meteorologicalDataResponse: MeteorologicalDataUI? = null,
    val isLoading: Boolean = false,
    val locationEnabled: Boolean = false,
    val errorState: ErrorState? = null
)