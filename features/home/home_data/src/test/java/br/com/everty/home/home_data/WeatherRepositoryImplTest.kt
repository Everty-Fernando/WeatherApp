package br.com.everty.home.home_data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.everty.home.home_data.model.MeteorologicalDataResponse
import br.com.everty.home.home_data.repository.WeatherRepository
import br.com.everty.home.home_data.repository.WeatherRepositoryImpl
import br.com.everty.home.home_data.service.WeatherAPI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val api: WeatherAPI = mockk()
    private val repository: WeatherRepository = WeatherRepositoryImpl(api)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() = runBlocking {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `given coordinates when API call is successful should return meteorological data`() = runBlocking {
        val latitude = 1.0
        val longitude = 2.0
        val mockResponse = mockk<MeteorologicalDataResponse>()

        coEvery {
            api.getMeteorologicalData(lat = latitude, lon = longitude, appId = any(), lang = any(), units = any())
        } returns Response.success(mockResponse)

        val result = repository.getMeteorologicalData(latitude, longitude).toList()

        assertEquals(1, result.size)
        assertEquals(mockResponse, result.first())
    }

    @Test
    fun `given invalid request when API returns 400 should throw IllegalArgumentException`() = runBlocking {
        coEvery {
            api.getMeteorologicalData(lat = any(), lon = any(), appId = any(), lang = any(), units = any())
        } returns Response.error(400, "".toResponseBody())

        val result = runCatching { repository.getMeteorologicalData(0.0, 0.0).toList() }

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        assertEquals("Solicitação inválida. Verifique os parâmetros da solicitação.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `given invalid token when API returns 401 should throw SecurityException`() = runBlocking {
        coEvery {
            api.getMeteorologicalData(lat = any(), lon = any(), appId = any(), lang = any(), units = any())
        } returns Response.error(401, "".toResponseBody())

        val result = runCatching { repository.getMeteorologicalData(0.0, 0.0).toList() }

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is SecurityException)
        assertEquals("Token de API não autorizado ou ausente.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `given non-existent data when API returns 404 should throw NoSuchElementException`() = runBlocking {
        coEvery {
            api.getMeteorologicalData(lat = any(), lon = any(), appId = any(), lang = any(), units = any())
        } returns Response.error(404, "".toResponseBody())

        val result = runCatching { repository.getMeteorologicalData(0.0, 0.0).toList() }

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is NoSuchElementException)
        assertEquals("Dados não encontrados para os parâmetros fornecidos.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `given too many requests when API returns 429 should throw IllegalStateException`() = runBlocking {
        coEvery {
            api.getMeteorologicalData(lat = any(), lon = any(), appId = any(), lang = any(), units = any())
        } returns Response.error(429, "".toResponseBody())

        val result = runCatching { repository.getMeteorologicalData(0.0, 0.0).toList() }

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalStateException)
        assertEquals("Muitas solicitações realizadas. Aguarde antes de tentar novamente.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `given server error when API returns 500 should throw IllegalStateException`() = runBlocking {
        coEvery {
            api.getMeteorologicalData(lat = any(), lon = any(), appId = any(), lang = any(), units = any())
        } returns Response.error(500, "".toResponseBody())

        val result = runCatching { repository.getMeteorologicalData(0.0, 0.0).toList() }

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalStateException)
        assertEquals("Erro inesperado no servidor. Tente novamente mais tarde.", result.exceptionOrNull()?.message)
    }
}
