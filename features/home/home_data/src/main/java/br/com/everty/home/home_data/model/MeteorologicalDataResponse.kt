package br.com.everty.home.home_data.model

import com.google.gson.annotations.SerializedName

data class MeteorologicalDataResponse(
    @SerializedName("current")
    val current: WeatherDataResponse,
    @SerializedName("hourly")
    val hourly: List<WeatherDataResponse>,
    @SerializedName("daily")
    val daily: List<WeatherDailyResponse>
)