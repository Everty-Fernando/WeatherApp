package br.estudo.everty.app_weather.home.data.service

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("onecall")
    suspend fun getMeteorologicalData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "pt_br",
        @Query("units") units: String = "metric",
        @Query("appid") appId: String,
    ): Response<MeteorologicalDataResponse>
}