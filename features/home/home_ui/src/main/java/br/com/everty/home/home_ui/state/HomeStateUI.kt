package br.com.everty.home.home_ui.state

import br.com.everty.home.home_domain.model.MeteorologicalDataUI
import br.com.everty.home.home_domain.model.WeatherTimelineUI
import br.com.everty.home.home_ui.model.ErrorState
import br.com.everty.home.home_ui.model.FilterType

data class HomeStateUI(
    val meteorologicalDataUI: MeteorologicalDataUI? = null,
    val cityName: String = "",
    val filterSelected: FilterType = FilterType.DAILY,
    val isLoading: Boolean = false,
    val isLoadingFilters: Boolean = false,
    val listWeathersFiltered: List<WeatherTimelineUI> = listOf(),
    val locationEnabled: Boolean = false,
    val errorState: ErrorState? = null
)