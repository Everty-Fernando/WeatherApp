package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.TemperatureResponse
import br.com.everty.home.home_data.model.WeatherDailyResponse
import br.com.everty.home.home_data.model.WeatherResponse
import br.com.everty.shared.presentation.R
import br.com.everty.shared.utils.extensions.convertTimestampToLocalDate
import br.com.everty.shared.utils.extensions.firstLetterUpperCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.TextStyle
import java.util.Locale

class WeatherDataDailyUIMapperTest {

    private val weatherImageUIMapper: WeatherImageUIMapper = mockk()
    private lateinit var mapper: WeatherDataDailyUIMapper

    @Before
    fun setup() {
        mapper = WeatherDataDailyUIMapper(weatherImageUIMapper)
    }

    val temperatureResponse = TemperatureResponse(
        day = 25.3,
        min = 18.5,
        max = 30.2,
        night = 20.1,
        eve = 24.8,
        morn = 19.7
    )

    @Test
    fun `given valid WeatherDailyResponse when mapped should return correct WeatherTimelineUI`() {
        val date = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        val weather = WeatherResponse(main = "Clear", description = "clear sky")

        val weatherDailyResponse = WeatherDailyResponse(
            date = date,
            temperature = temperatureResponse,
            weather = listOf(weather),
            sunrise = 1736985600L,
            sunset = 1737028800L,
            pressure = 1013.0,
            humidity = 75.0,
            uvi = 5.6,
            clouds = "80",
            windSpeed = "15",
        )

        every { weatherImageUIMapper.toObject("Clear") } returns R.drawable.sun

        val result = mapper.toObject(weatherDailyResponse)

        assertEquals("Hoje", result.date)
        assertEquals("30°/19°", result.temperature)
        assertEquals(R.drawable.sun, result.icon)
    }

    @Test
    fun `given WeatherDailyResponse for tomorrow's date when mapped should return 'Am ' as date`() {
        val tomorrow = LocalDateTime.now().plusDays(1).toEpochSecond(ZoneOffset.UTC)
        val weather = WeatherResponse(main = "Clouds", description = "cloudy sky")

        val weatherDailyResponse = WeatherDailyResponse(
            date = tomorrow,
            temperature = temperatureResponse,
            weather = listOf(weather),
            sunrise = 1736985600L,
            sunset = 1737028800L,
            pressure = 1013.0,
            humidity = 75.0,
            uvi = 5.6,
            clouds = "80",
            windSpeed = "15",
        )

        every { weatherImageUIMapper.toObject("Clouds") } returns R.drawable.sun_clouds

        val result = mapper.toObject(weatherDailyResponse)

        assertEquals("Am.", result.date)
        assertEquals("30°/19°", result.temperature)
        assertEquals(R.drawable.sun_clouds, result.icon)
    }

    @Test
    fun `given WeatherDailyResponse for other day when mapped should return day of the week as date`() {
        val specificDay = LocalDateTime.now().plusDays(3).toEpochSecond(ZoneOffset.UTC)
        val weather = WeatherResponse(main = "Rain", description = "rainy day")

        val weatherDailyResponse = WeatherDailyResponse(
            date = specificDay,
            temperature = temperatureResponse,
            weather = listOf(weather),
            sunrise = 1736985600L,
            sunset = 1737028800L,
            pressure = 1013.0,
            humidity = 75.0,
            uvi = 5.6,
            clouds = "80",
            windSpeed = "15",
        )

        every { weatherImageUIMapper.toObject("Rain") } returns R.drawable.rain

        val result = mapper.toObject(weatherDailyResponse)

        val expectedDay = specificDay.convertTimestampToLocalDate().dayOfWeek.getDisplayName(
            TextStyle.SHORT,
            Locale.getDefault()
        ).firstLetterUpperCase()

        assertEquals(expectedDay, result.date)
        assertEquals("30°/19°", result.temperature)
        assertEquals(R.drawable.rain, result.icon)
    }
}



