package br.com.everty.home.home_domain.model


data class MeteorologicalDataUI(
    val current: WeatherDataUI,
    val hourly: List<WeatherTimelineUI>,
    val daily: List<WeatherTimelineUI>
)
