package br.com.everty.home.home_domain.mappers

import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.model.WeatherDataResponse
import br.com.everty.home.home_domain.model.WeatherDataUI
import br.com.everty.home.home_domain.model.WeatherTimelineUI
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*

class MeteorologicalDataUIMapperTest {

    private val currentMapper: WeatherDataUIMapper = mockk()
    private val hoursMapper: WeatherDataHoursUIMapper = mockk()
    private val dayMapper: WeatherDataDailyUIMapper = mockk()

    private val mapper = MeteorologicalDataUIMapper(
        currentMapper = currentMapper,
        hoursMapper = hoursMapper,
        dayMapper = dayMapper
    )

    @Test
    fun `given valid MeteorologicalDataResponse when mapped then returns MeteorologicalDataUI`() {
        val mockResponse = mockk<MeteorologicalDataResponse>()
        val mockCurrent = mockk<WeatherDataUI>()
        val mockHourlyList = listOf(mockk<WeatherTimelineUI>())
        val mockDailyList = listOf(mockk<WeatherTimelineUI>())
        val mockHourlyResponse = listOf(mockk<WeatherDataResponse>())

        every { mockResponse.hourly } returns mockHourlyResponse
        every { currentMapper.toObject(mockResponse) } returns mockCurrent
        every { hoursMapper.toObjectList(mockHourlyResponse) } returns mockHourlyList
        every { dayMapper.toObjectList(mockResponse.daily) } returns mockDailyList

        val result = mapper.toObject(mockResponse)

        assertNotNull(result)
        assertEquals(mockCurrent, result.current)
        assertEquals(mockHourlyList, result.hourly)
        assertEquals(mockDailyList, result.daily)
    }


    @Test
    fun `given MeteorologicalDataResponse with null hourly and daily when mapped then returns empty lists`() {
        val mockResponse = MeteorologicalDataResponse(
            current = mockk(),
            hourly = emptyList(),
            daily = emptyList()
        )

        every { currentMapper.toObject(any()) } returns mockk()
        every { hoursMapper.toObjectList(any()) } returns emptyList()
        every { dayMapper.toObjectList(any()) } returns emptyList()

        val result = mapper.toObject(mockResponse)

        assertNotNull(result)
        assertEquals(emptyList<WeatherTimelineUI>(), result.hourly)
        assertEquals(emptyList<WeatherTimelineUI>(), result.daily)
    }


    @Test
    fun `given MeteorologicalDataResponse with empty hourly and daily lists when mapped then returns empty lists`() {
        val mockResponse = MeteorologicalDataResponse(
            current = mockk(),
            hourly = emptyList(),
            daily = emptyList()
        )

        every { currentMapper.toObject(any()) } returns mockk()
        every { hoursMapper.toObjectList(emptyList()) } returns emptyList()
        every { dayMapper.toObjectList(emptyList()) } returns emptyList()

        val result = mapper.toObject(mockResponse)

        assertNotNull(result)
        assertEquals(emptyList<WeatherTimelineUI>(), result.hourly)
        assertEquals(emptyList<WeatherTimelineUI>(), result.daily)
    }
}
