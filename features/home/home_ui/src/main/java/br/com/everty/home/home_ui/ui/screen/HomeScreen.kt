package br.com.everty.home.home_ui.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.everty.home.home_ui.events.HomeEvents
import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.home.home_ui.ui.components.HomeErrorContent
import br.com.everty.home.home_ui.ui.components.HomeFilters
import br.com.everty.home.home_ui.ui.components.HomeFooter
import br.com.everty.home.home_ui.ui.components.HomeFooterLoading
import br.com.everty.home.home_ui.ui.components.HomeHeader
import br.com.everty.home.home_ui.ui.components.HomeLoading
import br.com.everty.home.home_ui.ui.components.HomeSummaryWeatherContent
import br.com.everty.home.home_ui.ui.components.HomeTemperatureContent
import br.com.everty.home.home_ui.ui.preview.homeEventsPreview
import br.com.everty.home.home_ui.ui.preview.homeStateUIPreview
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.com.everty.shared.presentation.design_system.theme.backgrounds.BackgroundGradientLinearPrimary

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeStateUI: HomeStateUI,
    homeEvents: HomeEvents
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradientLinearPrimary()),
    ) {
        when {
            homeStateUI.errorState != null -> {
                HomeErrorContent(
                    errorState = homeStateUI.errorState,
                    onClick = homeEvents::onClickError
                )
            }
            homeStateUI.isLoading -> {
                HomeLoading()
            }
            homeStateUI.meteorologicalDataUI != null -> {
                HomeContent(
                    homeStateUI = homeStateUI,
                    onFilterSelected = homeEvents::onFilterSelected
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    homeStateUI: HomeStateUI,
    onFilterSelected: (Boolean) -> Unit
) {
    Column {
        HomeHeader(cityName = homeStateUI.cityName)

        homeStateUI.meteorologicalDataUI?.let { meteorologicalData ->
            HomeTemperatureContent(
                temperature = meteorologicalData.current.temperature,
                description = meteorologicalData.current.description,
                image = meteorologicalData.current.icon
            )

            HomeSummaryWeatherContent(weatherDataUI = meteorologicalData.current)

            HomeFilters(
                filterSelected = homeStateUI.filterSelected,
                onFilterWeeklySelected = onFilterSelected
            )

            if (homeStateUI.isLoadingFilters) HomeFooterLoading()
            else HomeFooter(weatherList = homeStateUI.listWeathersFiltered)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppWeatherTheme {
        HomeScreen(
            homeStateUI = homeStateUIPreview,
            homeEvents = homeEventsPreview
        )
    }
}