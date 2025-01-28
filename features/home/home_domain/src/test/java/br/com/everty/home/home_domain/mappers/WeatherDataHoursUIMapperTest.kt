package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.WeatherDataResponse
import br.com.everty.home.home_data.model.WeatherResponse
import br.com.everty.shared.presentation.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WeatherDataHoursUIMapperTest {

    private lateinit var weatherImageUIMapper: WeatherImageUIMapper
    private lateinit var mapper: WeatherDataHoursUIMapper

    @Before
    fun setup() {
        weatherImageUIMapper = mockk()
        mapper = WeatherDataHoursUIMapper(weatherImageUIMapper)
    }

    @Test
    fun `given valid WeatherDataResponse, when mapped, should return WeatherTimelineUI`() {
        val mockWeatherResponse = WeatherResponse(
            main = "Clear",
            description = "clear sky"
        )
        val mockWeatherDataResponse = WeatherDataResponse(
            date = 1677016569000,
            sunrise = 1676980800000,
            sunset = 1677024000000,
            temperature = 20.0,
            feelsLike = 293.15,
            pressure = 1013.0,
            humidity = 60.0,
            uvi = 5.0,
            clouds = "10",
            windSpeed = "3.6",
            weather = listOf(mockWeatherResponse)
        )
        val expectedIcon = R.drawable.ic_sun

        every { weatherImageUIMapper.toObject("Clear") } returns expectedIcon

        val result = mapper.toObject(mockWeatherDataResponse)

        assertEquals("20:00", result.date)
        assertEquals("20°", result.temperature)
        assertEquals(expectedIcon, result.icon)
    }

    @Test
    fun `given WeatherDataResponse with a different hour, when mapped, should return hour with 00`() {
        val mockWeatherResponse = WeatherResponse(
            main = "Rain",
            description = "light rain"
        )
        val mockWeatherDataResponse = WeatherDataResponse(
            date = System.currentTimeMillis() - 3600000,
            sunrise = 1676980800000,
            sunset = 1677024000000,
            temperature = 10.0,
            feelsLike = 283.15,
            pressure = 1013.0,
            humidity = 80.0,
            uvi = 5.0,
            clouds = "90",
            windSpeed = "4.2",
            weather = listOf(mockWeatherResponse)
        )
        val expectedIcon = R.drawable.rain

        every { weatherImageUIMapper.toObject("Rain") } returns expectedIcon

        val result = mapper.toObject(mockWeatherDataResponse)

        assertTrue(result.date.contains(":00"))
        assertEquals("10°", result.temperature)
        assertEquals(expectedIcon, result.icon)
    }
}




