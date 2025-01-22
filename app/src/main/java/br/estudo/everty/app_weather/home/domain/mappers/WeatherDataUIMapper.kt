package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.domain.model.WeatherDataUI
import br.com.everty.shared.utils.extensions.FORMAT_MONTH_YEAR_FULL
import br.com.everty.shared.utils.extensions.convertTimestampToString
import br.com.everty.shared.utils.extensions.firstLetterUpperCase
import br.com.everty.shared.utils.extensions.toDegreeCelsius
import br.com.everty.shared.utils.mappers.Mapper

class WeatherDataUIMapper(
    private val summaryMapper: SummaryUIMapper,
    private val weatherImageUIMapper: WeatherImageUIMapper
): Mapper<MeteorologicalDataResponse, WeatherDataUI> {

    override fun toObject(fromObject: MeteorologicalDataResponse): WeatherDataUI {
        fromObject.current.apply {
            return WeatherDataUI(
                date = date.convertTimestampToString(FORMAT_MONTH_YEAR_FULL),
                listSummary = summaryMapper.toObject(fromObject),
                temperature = temperature.toDegreeCelsius(),
                description = weather.first().description.firstLetterUpperCase(),
                icon = weatherImageUIMapper.toObject(weather.first().main),
            )
        }
    }
}
