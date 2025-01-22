package br.estudo.everty.app_weather.home.ui.screens.preview

import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.home.domain.model.ItemSummaryUI
import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.estudo.everty.app_weather.home.domain.model.WeatherDataUI
import br.estudo.everty.app_weather.home.domain.model.WeatherTimelineUI
import br.estudo.everty.app_weather.home.ui.screens.events.HomeEvents
import br.estudo.everty.app_weather.home.ui.screens.state.HomeStateUI


val item1 = ItemSummaryUI(
    "min/Máx","-3°/3°", R.drawable.ic_thermometer
)
val item2 = ItemSummaryUI(
    "Pôr do sol","06:00pm", R.drawable.ic_sun
)
val item3 = ItemSummaryUI(
    "Chuva","24%", R.drawable.ic_cloud
)
val item4 = ItemSummaryUI(
    "Nascer do sol","06:00pm", R.drawable.ic_sun
)
val item5 = ItemSummaryUI(
    "Vento","20km/h", R.drawable.ic_wind
)
val item6 = ItemSummaryUI(
    "Umidade","80%", R.drawable.ic_drop
)

val listItemsSummaryPreview = listOf(item1, item2, item3, item4, item5, item6)

val currentWeatherPreview = WeatherDataUI(
    date = "Oct, 10",
    listSummary = listItemsSummaryPreview,
    temperature = "13°",
    description = "Parcialmente Nublado",
    icon = R.drawable.sun_clouds
)

val hourlyWeatherTimelinePreview = listOf(
    WeatherTimelineUI("Agora", "13°", R.drawable.sun_clouds),
    WeatherTimelineUI("4PM", "13°", R.drawable.sun),
    WeatherTimelineUI("6PM", "13°", R.drawable.sun),
    WeatherTimelineUI("8PM", "13°", R.drawable.ic_cloud),
    WeatherTimelineUI("10PM", "13°", R.drawable.rain),
    WeatherTimelineUI("12PM", "13°", R.drawable.rain)
)

val dailyWeatherTimelinePreview = listOf(
    WeatherTimelineUI("Hoje", "13°", R.drawable.sun_clouds),
    WeatherTimelineUI("Amanhã", "14°", R.drawable.rain),
    WeatherTimelineUI("Segunda", "12°", R.drawable.rain),
    WeatherTimelineUI("Terça", "15°", R.drawable.rain),
    WeatherTimelineUI("Quarta", "11°", R.drawable.thunder),
    WeatherTimelineUI("Quinta", "13°", R.drawable.thunder)
)

val meteorologicalDataPreview = MeteorologicalDataUI(
    current = currentWeatherPreview,
    hourly = hourlyWeatherTimelinePreview,
    daily = dailyWeatherTimelinePreview
)

val homeStateUIPreview = HomeStateUI(
    meteorologicalDataUI = meteorologicalDataPreview,
    cityName = "Mairinque",
    listWeathersFiltered = dailyWeatherTimelinePreview,
    locationEnabled = true,
    errorState = null
)

val homeEventsPreview = object: HomeEvents {
    override fun onClickError() {}

    override fun onFilterSelected(isDaily: Boolean) {}
}