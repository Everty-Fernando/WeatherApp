package br.estudo.everty.app_weather.home.domain.model

import br.estudo.everty.app_weather.home.data.model.ItemSummary


data class WeatherDataUI(
    val date: String,
    val listSummary: List<ItemSummary>,
//    val sunrise: String,
//    val sunset: String,
    val temperature: String,
    //val feelsLike: String,
    //val pressure: String,
//    val humidity: String,
    //val uvi: String,
//    val clouds: String,
//    val windSpeed: String,
    val description: String,
    val icon: Int
)