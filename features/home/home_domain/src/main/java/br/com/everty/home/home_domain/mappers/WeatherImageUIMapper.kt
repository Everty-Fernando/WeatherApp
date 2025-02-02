package br.com.everty.home.home_domain.mappers


import br.com.everty.shared.presentation.R
import br.com.everty.shared.utils.mappers.Mapper

class WeatherImageUIMapper: Mapper<Int, Int> {

    override fun toObject(fromObject: Int): Int {
        return when(fromObject) {
            // ☀️ Sol - Céu Limpo
            800 -> R.drawable.sun

            // 🌤️ Sol - Com Nuvens
            801, 802 -> R.drawable.sun_clouds

            // ☁️ Nublado
            803, 804 -> R.drawable.clouds

            // 🌦️ Chuva fraca
            in 300..321, 500, 501, 520, 521, 531 -> R.drawable.light_rain

            // 🌧️ Chuva forte
            in 502 ..504, in 200..232, 522 -> R.drawable.heavy_rain

            // ❄️ Chuva fria
            511 -> R.drawable.freezing_rain

            // 🌫️ Outros - Default para Nublado
            else -> R.drawable.clouds
        }
    }
}
