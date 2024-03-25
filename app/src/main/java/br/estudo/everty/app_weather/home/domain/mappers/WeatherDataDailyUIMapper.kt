package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.home.data.model.TemperatureResponse
import br.estudo.everty.app_weather.home.data.model.WeatherDailyResponse
import br.estudo.everty.app_weather.home.domain.model.WeatherDataHoursUI
import br.estudo.everty.app_weather.utils.extensions.convertTimestampToLocalDate
import br.estudo.everty.app_weather.utils.extensions.firstLetterUpperCase
import br.estudo.everty.app_weather.utils.extensions.toDegreeCelsius
import br.estudo.everty.app_weather.utils.mappers.Mapper
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.TextStyle
import java.util.Locale

class WeatherDataDailyUIMapper(
    private val weatherImageUIMapper: WeatherImageUIMapper
): Mapper<WeatherDailyResponse, WeatherDataHoursUI> {

    override fun toObject(fromObject: WeatherDailyResponse): WeatherDataHoursUI {
        return WeatherDataHoursUI(
            date = checkCurrentDate(fromObject.date),
            temperature = getMinMaxTemperature(fromObject.temperature),
            icon = weatherImageUIMapper.toObject(fromObject.weather.first().main),
        )
    }

    private fun checkCurrentDate(date: Long): String {
        val dayCurrent = LocalDateTime.now().dayOfMonth
        val dateDay = date.convertTimestampToLocalDate()
        if (dayCurrent + 1 == dateDay.dayOfMonth) {
            return "Am."
        } else if(dayCurrent == dateDay.dayOfMonth) {
            return "Hoje"
        }
        return date.convertTimestampToLocalDate().dayOfWeek.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        ).firstLetterUpperCase()
    }

    private fun getMinMaxTemperature(temperatureResponse: TemperatureResponse): String {
        val min = temperatureResponse.min.toDegreeCelsius()
        val max = temperatureResponse.max.toDegreeCelsius()
        return "$max/$min"
    }
}
