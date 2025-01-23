package br.com.everty.home.home_domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.repository.WeatherRepository
import br.com.everty.home.home_domain.mappers.MeteorologicalDataUIMapper
import br.com.everty.home.home_domain.model.MeteorologicalDataUI
import br.com.everty.shared.presentation.R
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMeteorologicalDataUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var useCase: GetMeteorologicalDataUseCase
    private val repository: WeatherRepository = mockk()
    private val mapper: MeteorologicalDataUIMapper = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetMeteorologicalDataUseCase(repository, mapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given valid coordinates when invoked should return MeteorologicalDataUI`() = runTest {
        val lat = 1.0
        val lon = 2.0

        val meteorologicalDataResponse = mockk<MeteorologicalDataResponse>()
        val meteorologicalDataUI = mockk<MeteorologicalDataUI>()

        coEvery { repository.getMeteorologicalData(lat, lon) } returns flowOf(meteorologicalDataResponse)
        every { mapper.toObject(meteorologicalDataResponse) } returns meteorologicalDataUI

        val result = useCase(lat, lon).toList()

        assertEquals(1, result.size)
        assertEquals(meteorologicalDataUI, result.first())
        coVerify(exactly = 1) { repository.getMeteorologicalData(lat, lon) }
        verify(exactly = 1) { mapper.toObject(meteorologicalDataResponse) }
    }

    @Test
    fun `given valid coordinates when repository throws exception should propagate the error`() = runTest {
        val lat = 1.0
        val lon = 2.0
        val exception = Exception("Something went wrong")

        coEvery { repository.getMeteorologicalData(lat, lon) } throws exception

        val result = runCatching { useCase(lat, lon).toList() }

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { repository.getMeteorologicalData(lat, lon) }
        verify(exactly = 0) { mapper.toObject(any()) }
    }
}




