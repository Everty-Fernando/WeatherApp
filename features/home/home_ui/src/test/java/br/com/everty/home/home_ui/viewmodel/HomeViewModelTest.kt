package br.com.everty.home.home_ui.viewmodel

import android.app.Application
import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.everty.home.home_domain.model.MeteorologicalDataUI
import br.com.everty.home.home_domain.model.WeatherTimelineUI
import br.com.everty.home.home_domain.usecase.GetMeteorologicalDataUseCase
import br.com.everty.home.home_ui.model.ErrorState
import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.home.home_ui.ui.viewmodel.HomeViewModel
import br.com.everty.shared.utils.extensions.isConnected
import br.com.everty.shared.utils.helpers.LocationHelper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.After

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: HomeViewModel
    private val application: Application = mockk(relaxed = true)
    private val getMeteorologicalDataUseCase: GetMeteorologicalDataUseCase = mockk()
    private val locationHelper: LocationHelper = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(application, getMeteorologicalDataUseCase, locationHelper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given location is disabled when checkLocationEnabled is called should set LOCATION_DISABLED error`() {
        every { locationHelper.isLocationEnabled() } returns false

        viewModel.checkLocationEnabled()

        assertEquals(ErrorState.LocationDisabled, viewModel.homeStateUI.errorState)
    }

    @Test
    fun `given location is enabled when checkLocationEnabled is called should clean error state`() {
        every { locationHelper.isLocationEnabled() } returns true

        viewModel.checkLocationEnabled()

        assertNull(viewModel.homeStateUI.errorState)
    }

    @Test
    fun `given permission granted when requestPermissionLocale is called should invoke onPermissionGranted`() {
        val onPermissionGranted = mockk<() -> Unit>(relaxed = true)
        every {
            locationHelper.checkAndRequestPermissionLocale(
                any(),
                onPermissionGranted = captureLambda(),
                onPermissionDenied = any()
            )
        } answers {
            lambda<() -> Unit>().invoke()
        }

        viewModel.requestPermissionLocale(mockk(), onPermissionGranted)

        verify(exactly = 1) { onPermissionGranted.invoke() }
    }

    @Test
    fun `given permission denied when requestPermissionLocale is called should set LOCATION_PERMISSION_DENIED error`() {
        every {
            locationHelper.checkAndRequestPermissionLocale(
                any(),
                onPermissionGranted = any(),
                onPermissionDenied = captureLambda()
            )
        } answers {
            lambda<() -> Unit>().invoke()
        }

        viewModel.requestPermissionLocale(mockk(), mockk())

        assertEquals(ErrorState.LocationPermissionDenied, viewModel.homeStateUI.errorState)
    }

    @Test
    fun `given valid location when getDeviceLocation is called should fetch city name and data`() {
        val location = mockk<Location>(relaxed = true).apply {
            every { latitude } returns 10.0
            every { longitude } returns 20.0
        }
        every {
            locationHelper.getLastKnownLocation(
                onLocationRetrieved = captureLambda(),
                onError = any()
            )
        } answers {
            lambda<(Location) -> Unit>().invoke(location)
        }

        every {
            locationHelper.getCityName(
                latitude = 10.0,
                longitude = 20.0,
                onCityNameRetrieved = captureLambda()
            )
        } answers {
            lambda<(String) -> Unit>().invoke("MockCity")
        }

        every { application.isConnected() } returns false

        viewModel.getDeviceLocation()

        assertEquals(10.0, viewModel.latitude, 0.0)
        assertEquals(20.0, viewModel.longitude, 0.0)
        assertEquals("MockCity", viewModel.homeStateUI.cityName)
    }


    @Test
    fun `given location retrieval fails when getDeviceLocation is called should set ERROR_GET_LOCATION error`() {
        every { locationHelper.getLastKnownLocation(any(), any()) } answers {
            secondArg<() -> Unit>().invoke()
        }

        viewModel.getDeviceLocation()

        assertEquals(ErrorState.LocationGetError, viewModel.homeStateUI.errorState)
    }

    @Test
    fun `given network is not connected when checkNetworkAndFetchData is called should set NETWORK_DISABLED error`() {
        every { application.isConnected() } returns false

        viewModel.checkNetworkAndFetchData()

        assertEquals(ErrorState.NetworkDisabled, viewModel.homeStateUI.errorState)
    }

    @Test
    fun `given network is connected when checkNetworkAndFetchData is called should fetch meteorological data`() =
        runTest {
            val mockMeteorologicalDataUI = mockk<MeteorologicalDataUI>(relaxed = true)
            every { application.isConnected() } returns true
            coEvery { getMeteorologicalDataUseCase.invoke(any(), any()) } returns flowOf(mockMeteorologicalDataUI)

            viewModel.checkNetworkAndFetchData()

            assertNull(viewModel.homeStateUI.errorState)
            assertEquals(mockMeteorologicalDataUI, viewModel.homeStateUI.meteorologicalDataUI)
        }


    @Test
    fun `given isDaily is true when onFilterSelected is called should filter daily weather`() {
        val dailyWeather = listOf(mockk<WeatherTimelineUI>())
        val meteorologicalDataUI = mockk<MeteorologicalDataUI> {
            every { daily } returns dailyWeather
        }
        val initialState = HomeStateUI(meteorologicalDataUI = meteorologicalDataUI)
        val viewModel = HomeViewModel(application, getMeteorologicalDataUseCase, locationHelper, initialState)

        viewModel.onFilterSelected(true)

        assertEquals(dailyWeather, viewModel.homeStateUI.listWeathersFiltered)
    }


    @Test
    fun `given isDaily is false when onFilterSelected is called should filter hourly weather`() {
        val hourlyWeather = listOf(mockk<WeatherTimelineUI>())
        val meteorologicalDataUI = mockk<MeteorologicalDataUI> {
            every { hourly } returns hourlyWeather
        }

        val initialState = HomeStateUI(meteorologicalDataUI = meteorologicalDataUI)
        val viewModel = HomeViewModel(application, getMeteorologicalDataUseCase, locationHelper, initialState)

        viewModel.onFilterSelected(false)

        assertEquals(hourlyWeather, viewModel.homeStateUI.listWeathersFiltered)
    }
}
