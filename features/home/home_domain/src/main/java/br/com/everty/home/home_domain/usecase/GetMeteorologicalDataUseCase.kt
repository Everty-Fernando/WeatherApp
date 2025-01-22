package br.com.everty.home.home_domain.usecase

import br.com.everty.home.home_data.repository.WeatherRepository
import br.com.everty.home.home_domain.mappers.MeteorologicalDataUIMapper
import br.com.everty.home.home_domain.model.MeteorologicalDataUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMeteorologicalDataUseCase(
    private val repository: WeatherRepository,
    private val mapper: MeteorologicalDataUIMapper
) {

    operator fun invoke(lat: Double, lon: Double) = getMeteorologicalDataUI(lat, lon)


    private fun getMeteorologicalDataUI(lat: Double, lon: Double): Flow<MeteorologicalDataUI> =
        repository.getMeteorologicalData(lat, lon).map {
            mapper.toObject(it)
        }
}
