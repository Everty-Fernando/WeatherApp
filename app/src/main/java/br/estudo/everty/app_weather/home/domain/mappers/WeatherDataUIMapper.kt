package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.domain.model.WeatherDataUI
import br.estudo.everty.app_weather.utils.extensions.FORMAT_MONTH_YEAR_FULL
import br.estudo.everty.app_weather.utils.extensions.convertTimestampToString
import br.estudo.everty.app_weather.utils.extensions.firstLetterUpperCase
import br.estudo.everty.app_weather.utils.extensions.toDegreeCelsius
import br.estudo.everty.app_weather.utils.mappers.Mapper

class WeatherDataUIMapper(
    private val summaryMapper: SummaryUIMapper,
    private val weatherImageUIMapper: WeatherImageUIMapper
): Mapper<MeteorologicalDataResponse, WeatherDataUI> {

    override fun toObject(fromObject: MeteorologicalDataResponse): WeatherDataUI {
        fromObject.current.apply {
            return WeatherDataUI(
                date = date.convertTimestampToString(FORMAT_MONTH_YEAR_FULL),
                listSummary = summaryMapper.toObject(fromObject),
//            sunrise = fromObject.sunrise.convertTimestampToString(FORMAT_HOURS),
//            sunset = fromObject.sunset.convertTimestampToString(FORMAT_HOURS),
                temperature = temperature.toDegreeCelsius(),
//            humidity = "${ fromObject.humidity.toInt()}%",
//            clouds = "${ fromObject.clouds.toInt()}%",
//            windSpeed = "${ fromObject.windSpeed} km/h",
                description = weather.first().description.firstLetterUpperCase(),
                icon = weatherImageUIMapper.toObject(weather.first().main),
            )
        }
    }
}
