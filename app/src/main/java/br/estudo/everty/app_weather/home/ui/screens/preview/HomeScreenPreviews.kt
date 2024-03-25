package br.estudo.everty.app_weather.home.ui.screens.preview

import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.home.data.model.ItemSummary
import br.estudo.everty.app_weather.home.data.model.Summary
import br.estudo.everty.app_weather.home.data.model.Weather

val item1 = ItemSummary(
    "","-3°/3°", R.drawable.ic_thermometer
)
val item2 = ItemSummary(
    "Pôr do sol","06:00pm", R.drawable.ic_sun
)
val item3 = ItemSummary(
    "Chuva","24%", R.drawable.ic_cloud
)
val item4 = ItemSummary(
    "Nascer do sol","06:00pm", R.drawable.ic_sun
)
val item5 = ItemSummary(
    "Vento","20km/h", R.drawable.ic_wind
)
val item6 = ItemSummary(
    "Umidade","80%", R.drawable.ic_drop
)

val listItemsSummary = listOf(item1, item2, item3, item4, item5, item6)

val summaryPreview = Summary(
    date = "Oct, 10",
    itemsSummary = listItemsSummary
)

val weatherDaily1 = Weather("Agora", "13°")
val weatherDaily2 = Weather("4PM", "13°")
val weatherDaily3 = Weather("6PM", "13°")
val weatherDaily4 = Weather("8PM", "13°")
val weatherDaily5 = Weather("10PM", "13°")
val weatherDaily6 = Weather("12PM", "13°")

val listWeatherDaily =
    listOf(weatherDaily1, weatherDaily2, weatherDaily3, weatherDaily4, weatherDaily5, weatherDaily6)

val weatherDailyPreview = listWeatherDaily

val weatherWeekly1 = Weather("Hoje", "13°")
val weatherWeekly2 = Weather("Aman.", "13°")
val weatherWeekly3 = Weather("Seg.", "13°")
val weatherWeekly4 = Weather("Ter.", "13°")
val weatherWeekly5 = Weather("Quart.", "13°")
val weatherWeekly6 = Weather("Qui.", "13°")

val listWeatherWeekly =
    listOf(weatherWeekly1, weatherWeekly2, weatherWeekly3, weatherWeekly4, weatherWeekly5, weatherWeekly6)
