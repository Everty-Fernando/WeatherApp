package br.estudo.everty.app_weather.home.domain.model

import br.estudo.everty.app_weather.home.data.model.TemperatureResponse
import br.estudo.everty.app_weather.home.data.model.WeatherResponse
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class WeatherDailyUI(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val temperature: TemperatureResponse,
    val pressure: Double,
    val humidity: Double,
    val uvi: Double,
    val clouds: String,
    val windSpeed: String,
    val weather: List<WeatherResponse>,
)