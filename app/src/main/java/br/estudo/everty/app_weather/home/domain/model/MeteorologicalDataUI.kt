package br.estudo.everty.app_weather.home.domain.model


data class MeteorologicalDataUI(
    val current: WeatherDataUI,
    val hourly: List<WeatherDataHoursUI>,
    val daily: List<WeatherDataHoursUI>
)
