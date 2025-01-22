package br.com.everty.home.home_data.repository

import br.com.everty.home.home_data.BuildConfig
import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.service.WeatherAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class WeatherRepositoryImpl(private val api: WeatherAPI): WeatherRepository {

    override fun getMeteorologicalData(lat: Double, lon: Double): Flow<MeteorologicalDataResponse> = flow {
        try {
            val response = api.getMeteorologicalData(
                lat = lat,
                lon = lon,
                appId = BuildConfig.API_KEY
            )
            val dataResponse = response.body()
            if (response.isSuccessful && dataResponse != null) {
                emit(dataResponse)
            } else {// Como está sendo um uso simples, acabei não fazendo o mapeamento dos erros, para não exibir apenas um erro genérico para o usuário
                throw Exception()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception()
        }
    }
}