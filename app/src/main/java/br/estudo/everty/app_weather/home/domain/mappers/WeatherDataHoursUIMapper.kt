package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.home.data.model.WeatherDataResponse
import br.estudo.everty.app_weather.home.domain.model.WeatherTimelineUI
import br.com.everty.shared.utils.extensions.FORMAT_HOURS
import br.com.everty.shared.utils.extensions.convertTimestampToString
import br.com.everty.shared.utils.extensions.toDegreeCelsius
import br.com.everty.shared.utils.mappers.Mapper
import org.threeten.bp.LocalTime

class WeatherDataHoursUIMapper(
    private val weatherImageUIMapper: WeatherImageUIMapper
): Mapper<WeatherDataResponse, WeatherTimelineUI> {

    override fun toObject(fromObject: WeatherDataResponse): WeatherTimelineUI {
        return WeatherTimelineUI(
            date = checkCurrentDate(fromObject.date),
            temperature = fromObject.temperature.toDegreeCelsius(),
            icon = weatherImageUIMapper.toObject(fromObject.weather.first().main),
        )
    }

    private fun checkCurrentDate(date: Long): String {
        val hourCurrent = LocalTime.now().hour
        val hourDate = date.convertTimestampToString(FORMAT_HOURS)
        if (hourCurrent == hourDate.toInt()) {
            return "Agora"
        }
        return  "$hourDate:00"
    }
}
