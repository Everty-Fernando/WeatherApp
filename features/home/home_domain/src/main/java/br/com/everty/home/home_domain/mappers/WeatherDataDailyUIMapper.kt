package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.TemperatureResponse
import br.com.everty.home.home_data.model.WeatherDailyResponse
import br.com.everty.shared.utils.extensions.convertTimestampToLocalDate
import br.com.everty.shared.utils.extensions.firstLetterUpperCase
import br.com.everty.shared.utils.extensions.toDegreeCelsius
import br.com.everty.shared.utils.mappers.Mapper
import br.com.everty.home.home_domain.model.WeatherTimelineUI
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.TextStyle
import java.util.Locale

class WeatherDataDailyUIMapper(
    private val weatherImageUIMapper: WeatherImageUIMapper
): Mapper<WeatherDailyResponse, WeatherTimelineUI> {

    override fun toObject(fromObject: WeatherDailyResponse): WeatherTimelineUI {
        return WeatherTimelineUI(
            date = checkCurrentDate(fromObject.date),
            temperature = getMinMaxTemperature(fromObject.temperature),
            icon = weatherImageUIMapper.toObject(fromObject.weather.first().id),
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
