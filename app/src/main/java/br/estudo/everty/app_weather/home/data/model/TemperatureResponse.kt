package br.estudo.everty.app_weather.home.data.model

import com.google.gson.annotations.SerializedName

data class TemperatureResponse (
    @SerializedName("day")
    val day: Double,
    @SerializedName("min")
    val min: Double,
    @SerializedName("max")
    val max: Double,
    @SerializedName("night")
    val night: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("morn")
    val morn: Double,
)