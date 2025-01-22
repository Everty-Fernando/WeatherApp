package br.estudo.everty.app_weather.home.domain.mappers

import br.estudo.everty.app_weather.R
import br.com.everty.shared.utils.mappers.Mapper

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
