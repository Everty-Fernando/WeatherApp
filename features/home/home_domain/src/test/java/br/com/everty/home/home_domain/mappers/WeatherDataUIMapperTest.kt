package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.model.WeatherDataResponse
import br.com.everty.home.home_data.model.WeatherResponse
import br.com.everty.home.home_domain.model.ItemSummaryUI
import br.com.everty.shared.presentation.R
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class WeatherDataUIMapperTest {

    private val summaryMapper: SummaryUIMapper = mockk()
    private val weatherImageUIMapper: WeatherImageUIMapper = mockk()

    private val mapper = WeatherDataUIMapper(
        summaryMapper = summaryMapper,
        weatherImageUIMapper = weatherImageUIMapper
    )

    @Test
    fun `given valid WeatherDataResponse when mapped should return WeatherDataUI`() {
        val weatherDescription = WeatherResponse(
            main = "Clear",
            description = "clear sky"
        )
        val weatherDataResponse = WeatherDataResponse(
            date = 1633024800L,
            temperature = 25.0,
            weather = listOf(weatherDescription),
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
        val expectedSummary = listOf(mockk<ItemSummaryUI>())
        val expectedIcon = 123

        every { summaryMapper.toObject(meteorologicalDataResponse) } returns expectedSummary
        every { weatherImageUIMapper.toObject(weatherDescription.main) } returns expectedIcon

        val result = mapper.toObject(meteorologicalDataResponse)

        assertEquals("Setembro, 30", result.date)
        assertEquals(expectedSummary, result.listSummary)
        assertEquals("25°", result.temperature)
        assertEquals("Clear sky", result.description)
        assertEquals(expectedIcon, result.icon)
    }

    @Test
    fun `given null or empty weather data when mapped should handle gracefully`() {
        val weatherDataResponse = WeatherDataResponse(
            date = 1633024800L,
            temperature = 25.0,
            weather = listOf(),
            sunrise = 0,
            sunset = 0,
            pressure = 0.0,
            humidity = 0.0,
            clouds = "",
            windSpeed = "",
            feelsLike = 0.0,
            uvi = 0.0,
        )
        val meteorologicalDataResponse = MeteorologicalDataResponse(
            current = weatherDataResponse,
            hourly = emptyList(),
            daily = emptyList()
        )
        val expectedSummary = emptyList<ItemSummaryUI>()
        val defaultIcon = R.drawable.sun

        every { summaryMapper.toObject(meteorologicalDataResponse) } returns expectedSummary
        every { weatherImageUIMapper.toObject(any()) } returns defaultIcon

        val result = mapper.toObject(meteorologicalDataResponse)

        assertEquals("Setembro, 30", result.date)
        assertEquals(expectedSummary, result.listSummary)
        assertEquals("25°", result.temperature)
        assertEquals("", result.description)
        assertEquals(defaultIcon, result.icon)
    }
}

