package br.com.everty.home.home_data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)