package br.com.everty.home.home_data.service

import br.com.everty.home.home_data.model.MeteorologicalDataResponse
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
        @Query("exclude") exclude: String = "minutely",
        @Query("appid") appId: String,
    ): Response<MeteorologicalDataResponse>
}