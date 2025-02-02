package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.shared.utils.mappers.Mapper
import br.com.everty.home.home_domain.model.MeteorologicalDataUI

class MeteorologicalDataUIMapper(
    private val currentMapper: WeatherDataUIMapper,
    private val hoursMapper: WeatherDataHoursUIMapper,
    private val dayMapper: WeatherDataDailyUIMapper,
): Mapper<MeteorologicalDataResponse, MeteorologicalDataUI> {

    override fun toObject(fromObject: MeteorologicalDataResponse): MeteorologicalDataUI {
        return MeteorologicalDataUI(
            current = currentMapper.toObject(fromObject),
            hourly = hoursMapper.toObjectList(fromObject.hourly.take(24)),
            daily = dayMapper.toObjectList(fromObject.daily)
        )
    }
}
