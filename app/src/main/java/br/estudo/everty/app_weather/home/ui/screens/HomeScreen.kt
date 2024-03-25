package br.estudo.everty.app_weather.home.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.estudo.everty.app_weather.home.domain.model.WeatherDataHoursUI
import br.estudo.everty.app_weather.home.ui.screens.components.HomeErrorContent
import br.estudo.everty.app_weather.theme.backgrounds.BackgroundGradientLinearPrimary
import br.estudo.everty.app_weather.theme.AppWeatherTheme
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFilters
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFooter
import br.estudo.everty.app_weather.home.ui.screens.components.HomeFooterLoading
import br.estudo.everty.app_weather.home.ui.screens.components.HomeHeader
import br.estudo.everty.app_weather.home.ui.screens.components.HomeLoading
import br.estudo.everty.app_weather.home.ui.screens.components.HomeSummaryWeatherContent
import br.estudo.everty.app_weather.home.ui.screens.components.HomeTemperatureContent
import br.estudo.everty.app_weather.home.ui.screens.state.HomeStateUI
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(cityName: String, homeStateUI: HomeStateUI, onClickError: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradientLinearPrimary()),
    ) {
        if (homeStateUI.errorState != null) {
            HomeErrorContent(
                titleError = homeStateUI.errorState.title,
                descriptionError = homeStateUI.errorState.description,
                textButton = homeStateUI.errorState.textButton,
                iconError = homeStateUI.errorState.icon,
                onClick = onClickError
            )
        } else if (homeStateUI.isLoading) {
            HomeLoading()
        } else {
            homeStateUI.meteorologicalDataResponse?.let { meteorologicalData ->
                var filterSelectedIsDaily by remember {
                    mutableStateOf(true)
                }

                var isLoadingFooter by remember {
                    mutableStateOf(false)
                }

                var listWeather: List<WeatherDataHoursUI> by remember {
                    mutableStateOf(emptyList())
                }


                HomeHeader(cityName = cityName)
                HomeTemperatureContent(
                    temperature = meteorologicalData.current.temperature,
                    description = meteorologicalData.current.description,
                    image = meteorologicalData.current.icon
                )
                HomeSummaryWeatherContent(weatherDataUI = meteorologicalData.current)
                HomeFilters(onFilterDailySelected = { dailySelected ->
                    isLoadingFooter = true
                    filterSelectedIsDaily = dailySelected

                })
                if (isLoadingFooter) {
                    LaunchedEffect(Unit) {
                        delay(2000)
                        isLoadingFooter = false
                    }
                }


                listWeather = if (filterSelectedIsDaily) meteorologicalData.hourly
                else meteorologicalData.daily
                if (isLoadingFooter) {
                    HomeFooterLoading()
                } else {
                    HomeFooter(weatherList = listWeather)
                }

            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    AppWeatherTheme {
        //HomeScreen()
    }
}