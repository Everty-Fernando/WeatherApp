package br.estudo.everty.app_weather.home.data.repository

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getMeteorologicalData(lat: Double, lon: Double): Flow<MeteorologicalDataResponse>
}