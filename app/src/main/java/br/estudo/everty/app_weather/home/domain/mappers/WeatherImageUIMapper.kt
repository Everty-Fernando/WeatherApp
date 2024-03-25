package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.home.data.model.MeteorologicalDataResponse
import br.estudo.everty.app_weather.home.domain.model.WeatherDataUI
import br.estudo.everty.app_weather.utils.extensions.FORMAT_MONTH_YEAR_FULL
import br.estudo.everty.app_weather.utils.extensions.convertTimestampToString
import br.estudo.everty.app_weather.utils.extensions.firstLetterUpperCase
import br.estudo.everty.app_weather.utils.extensions.toDegreeCelsius
import br.estudo.everty.app_weather.utils.mappers.Mapper

class WeatherImageUIMapper: Mapper<String, Int> {

    override fun toObject(fromObject: String): Int {
        return when(fromObject) {
            "Clear" -> R.drawable.sun
            "Rain" -> R.drawable.rain
            "Clouds" -> R.drawable.sun_clouds
            "Thunderstorm" -> R.drawable.thunder
            else ->  R.drawable.sun
        }
    }
}
