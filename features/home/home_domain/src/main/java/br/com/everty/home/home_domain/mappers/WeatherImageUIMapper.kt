package br.com.everty.home.home_domain.mappers


import br.com.everty.shared.presentation.R
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
