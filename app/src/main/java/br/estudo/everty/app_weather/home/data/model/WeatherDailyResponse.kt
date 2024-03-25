package br.estudo.everty.app_weather.home.data.model

import com.google.gson.annotations.SerializedName

data class WeatherDailyResponse(
    @SerializedName("dt")
    val date: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("temp")
    val temperature: TemperatureResponse,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("clouds")
    val clouds: String,
    @SerializedName("wind_speed")
    val windSpeed: String,
    @SerializedName("weather")
    val weather: List<WeatherResponse>,
)