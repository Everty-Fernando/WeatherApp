package br.estudo.everty.app_weather.home.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.estudo.everty.app_weather.home.ui.screens.components.HomeErrorContent
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFilters
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFooter
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFooterLoading
import br.estudo.everty.app_weather.home.ui.screens.components.HomeHeader
import br.estudo.everty.app_weather.home.ui.screens.components.HomeLoading
import br.estudo.everty.app_weather.home.ui.screens.components.HomeSummaryWeatherContent
import br.estudo.everty.app_weather.home.ui.screens.components.HomeTemperatureContent
import br.estudo.everty.app_weather.home.ui.screens.events.HomeEvents
import br.estudo.everty.app_weather.home.ui.screens.preview.homeEventsPreview
import br.estudo.everty.app_weather.home.ui.screens.preview.homeStateUIPreview
import br.estudo.everty.app_weather.home.ui.screens.state.HomeStateUI
import br.estudo.everty.app_weather.theme.AppWeatherTheme
import br.estudo.everty.app_weather.theme.backgrounds.BackgroundGradientLinearPrimary

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

            HomeFilters(onFilterDailySelected = onFilterSelected)

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