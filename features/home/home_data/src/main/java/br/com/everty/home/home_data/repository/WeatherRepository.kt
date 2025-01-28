package br.com.everty.home.home_data.repository

import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getMeteorologicalData(lat: Double, lon: Double): Flow<MeteorologicalDataResponse>
}