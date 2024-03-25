package br.estudo.everty.app_weather.home.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)