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
    fun `given Clear weather type, when mapped, should return sun icon`() {
        val weatherType = "Clear"
        val expectedIcon = R.drawable.sun

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given Rain weather type, when mapped, should return rain icon`() {
        val weatherType = "Rain"
        val expectedIcon = R.drawable.rain

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given Clouds weather type, when mapped, should return sun clouds icon`() {
        val weatherType = "Clouds"
        val expectedIcon = R.drawable.sun_clouds

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given Thunderstorm weather type, when mapped, should return thunder icon`() {
        val weatherType = "Thunderstorm"
        val expectedIcon = R.drawable.thunder

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }

    @Test
    fun `given unknown weather type, when mapped, should return default sun icon`() {
        val weatherType = "UnknownType"
        val expectedIcon = R.drawable.sun

        val result = mapper.toObject(weatherType)

        assertEquals(expectedIcon, result)
    }
}



