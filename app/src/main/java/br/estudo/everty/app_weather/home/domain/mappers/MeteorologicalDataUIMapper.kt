package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.domain.model.MeteorologicalDataUI
import br.com.everty.shared.utils.mappers.Mapper

class MeteorologicalDataUIMapper(
    private val currentMapper: WeatherDataUIMapper,
    private val hoursMapper: WeatherDataHoursUIMapper,
    private val dayMapper: WeatherDataDailyUIMapper,
): Mapper<MeteorologicalDataResponse, MeteorologicalDataUI> {

    override fun toObject(fromObject: MeteorologicalDataResponse): MeteorologicalDataUI {
        return MeteorologicalDataUI(
            current = currentMapper.toObject(fromObject),
            hourly = hoursMapper.toObjectList(fromObject.hourly),
            daily = dayMapper.toObjectList(fromObject.daily)
        )
    }
}
