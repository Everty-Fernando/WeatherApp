package br.estudo.everty.app_weather.home.data.model

import com.google.gson.annotations.SerializedName

data class MeteorologicalDataResponse(
    @SerializedName("current")
    val current: WeatherDataResponse,
    @SerializedName("hourly")
    val hourly: List<WeatherDataResponse>,
    @SerializedName("daily")
    val daily: List<WeatherDailyResponse>
)