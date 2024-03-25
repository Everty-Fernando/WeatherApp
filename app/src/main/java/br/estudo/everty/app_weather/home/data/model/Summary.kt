package br.estudo.everty.app_weather.home.data.model

data class Summary(
    val date: String,
    val itemsSummary: List<ItemSummary>
//    val minMaxTemperature: String,
//    val sunRaise: String,
//    val cloud: String,
//    val sunSet: String,
//    val haze: String,
//    val drop: String
)