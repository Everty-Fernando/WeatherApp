package br.com.everty.home.home_domain.mappers

import br.com.everty.shared.presentation.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherImageUIMapperTest {

    private lateinit var mapper: WeatherImageUIMapper

    @Before
    fun setup() {
        mapper = WeatherImageUIMapper()
    }

    @Test
    fun `given Clear when mapped should return Sun icon`() {
        val weatherType = 800
        val expectedIcon = R.drawable.sun

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given Partially Cloudy when mapped should return Sun with Clouds icon`() {
        val weatherTypes = listOf(801, 802)
        val expectedIcon = R.drawable.sun_clouds

        weatherTypes.forEach { weatherType ->
            val result = mapper.toObject(weatherType)
            assertEquals(expectedIcon, result)
        }
    }

    @Test
    fun `given Cloudy when mapped should return Clouds icon`() {
        val weatherTypes = listOf(803, 804)
        val expectedIcon = R.drawable.clouds

        weatherTypes.forEach { weatherType ->
            val result = mapper.toObject(weatherType)
            assertEquals(expectedIcon, result)
        }
    }

    @Test
    fun `given Light Rain when mapped should return Light Rain icon`() {
        val weatherTypes = listOf(300, 301, 302, 310, 311, 312, 313, 314, 321, 500, 501, 520, 521, 531)
        val expectedIcon = R.drawable.light_rain

        weatherTypes.forEach { weatherType ->
            val result = mapper.toObject(weatherType)
            assertEquals(expectedIcon, result)
        }
    }

    @Test
    fun `given Heavy Rain or Thunderstorm when mapped should return Heavy Rain icon`() {
        val weatherTypes = listOf(200, 201, 202, 210, 211, 212, 221, 230, 231, 232, 502, 503, 504, 522)
        val expectedIcon = R.drawable.heavy_rain

        weatherTypes.forEach { weatherType ->
            val result = mapper.toObject(weatherType)
            assertEquals(expectedIcon, result)
        }
    }

    @Test
    fun `given Freezing Rain when mapped should return Freezing Rain icon`() {
        val weatherType = 511
        val expectedIcon = R.drawable.freezing_rain

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given Unknown weather type when mapped should return Default Clouds icon`() {
        val unknownWeatherTypes = listOf(999, -1, 1000, 700)
        val expectedIcon = R.drawable.clouds

        unknownWeatherTypes.forEach { weatherType ->
            val result = mapper.toObject(weatherType)
            assertEquals(expectedIcon, result)
        }
    }
}




