package br.estudo.everty.app_weather.home.data.repository

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.data.service.WeatherAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import br.estudo.everty.app_weather.BuildConfig


class WeatherRepositoryImpl(private val api: WeatherAPI): WeatherRepository {

    override suspend fun getMeteorologicalData(lat: Double, lon: Double): Flow<MeteorologicalDataResponse> = flow {
        try {
            val response = api.getMeteorologicalData(
                lat = lat,
                lon = lon,
                appId = BuildConfig.API_KEY
            )
            val dataResponse = response.body()
            if (response.isSuccessful && dataResponse != null) {
                emit(dataResponse)
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception()
        }
    }
}