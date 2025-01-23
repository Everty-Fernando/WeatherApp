package br.com.everty.home.home_domain.mappers

import android.content.Context
import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.model.TemperatureResponse
import br.com.everty.home.home_data.model.WeatherDailyResponse
import br.com.everty.home.home_data.model.WeatherDataResponse
import br.com.everty.home.home_data.model.WeatherResponse
import br.com.everty.home.home_domain.model.ItemSummaryUI
import br.com.everty.shared.presentation.R
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SummaryUIMapperTest {

    private val context: Context = mockk()
    private lateinit var mapper: SummaryUIMapper

    @Before
    fun setup() {
        every { context.getString(R.string.item_summary_min_max) } returns "Min/Max"
        every { context.getString(R.string.item_summary_sun_set) } returns "Pôr do Sol"
        every { context.getString(R.string.item_summary_sun_rise) } returns "Nascer do Sol"
        every { context.getString(R.string.item_summary_rain) } returns "Chuva"
        every { context.getString(R.string.item_summary_wind) } returns "Vento"
        every { context.getString(R.string.item_summary_drop) } returns "Umidade"

        mapper = SummaryUIMapper(context)
    }

    @Test
    fun `given valid MeteorologicalDataResponse when mapped should return correct ItemSummaryUI list`() {
        val dailyResponse = WeatherDailyResponse(
            date = 1633024800L,
            sunrise = 1633003200L,
            sunset = 1633046400L,
            temperature = TemperatureResponse(
                day = 25.0,
                min = 18.0,
                max = 30.0,
                night = 20.0,
                eve = 24.0,
                morn = 19.0
            ),
            pressure = 1013.0,
            humidity = 75.0,
            uvi = 5.6,
            clouds = "80",
            windSpeed = "15",
            weather = listOf(
                WeatherResponse(
                    main = "Clouds",
                    description = "broken clouds"
                )
            )
        )

        val currentWeather = WeatherDataResponse(
            date = 1633024800L,
            temperature = 25.0,
            sunrise = 1633003200L,
            sunset = 1633046400L,
            pressure = 1013.0,
            humidity = 78.0,
            clouds = "78",
            windSpeed = "10",
            feelsLike = 25.0,
            uvi = 5.0,
            weather = listOf(
                WeatherResponse(
                    main = "Clear",
                    description = "clear sky"
                )
            )
        )

        val meteorologicalDataResponse = MeteorologicalDataResponse(
            current = currentWeather,
            hourly = emptyList(),
            daily = listOf(dailyResponse)
        )


        val result = mapper.toObject(meteorologicalDataResponse)

        assertEquals("18°/30°", result[0].value)
        assertEquals("21:09", result[1].value)
        assertEquals("78%", result[2].value)
        assertEquals("09:09", result[3].value)
        assertEquals("10 km/h", result[4].value)
        assertEquals("78%", result[5].value)
    }


    @Test
    fun `given MeteorologicalDataResponse with missing daily data when mapped should handle gracefully`() {
        val weatherDataResponse = WeatherDataResponse(
            date = 1633024800L,
            temperature = 25.0,
            weather = listOf(),
            sunrise = 1633003200L,
            sunset = 1633046400L,
            pressure = 1013.0,
            humidity = 78.0,
            clouds = "78",
            windSpeed = "78",
            feelsLike = 7.0,
            uvi = 7.0,
        )
        val meteorologicalDataResponse = MeteorologicalDataResponse(
            current = weatherDataResponse,
            hourly = emptyList(),
            daily = emptyList()
        )

        val result = mapper.toObject(meteorologicalDataResponse)

        assertEquals("", result[0].value)
        assertEquals("21:09", result[1].value)
        assertEquals("78%", result[2].value)
        assertEquals("09:09", result[3].value)
        assertEquals("78 km/h", result[4].value)
        assertEquals("78%", result[5].value)
    }
}


