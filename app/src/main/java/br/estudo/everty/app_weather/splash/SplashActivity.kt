package br.estudo.everty.app_weather.splash

import android.Manifest
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import br.estudo.everty.app_weather.theme.AppWeatherTheme
import br.estudo.everty.app_weather.home.ui.screens.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import br.estudo.everty.app_weather.home.ui.HomeActivity
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState
import br.estudo.everty.app_weather.utils.extensions.isConnected
import br.estudo.everty.app_weather.utils.extensions.openLocationSettings
import br.estudo.everty.app_weather.utils.extensions.redirectToAppSettings
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale


class SplashActivity : ComponentActivity() {
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppWeatherTheme {
                SplashScreen(onNavigateToHome = {
                    startActivity(Intent(this, HomeActivity::class.java))
                })
            }
        }
    }

    private fun onClickError() {
        when(viewModel.homeStateUI.errorState) {
            ErrorState.LOCATION_DISABLED -> {
                openLocationSettings(this)
            }
            ErrorState.LOCATION_PERMISSION_DENIED -> {
                redirectToAppSettings(this)
            }
            else -> {
                viewModel.cleanErrorState()
                viewModel.setLoading(true)

                lifecycleScope.launch {
                    delay(1000)
                    if (viewModel.latitude != 0.0 && viewModel.longitude != 0.0) {
                        viewModel.getMeteorologicalData()
                    } else {
                        requestPermissionLocale()
                    }
                }
            }
        }
    }

    private fun requestPermissionLocale() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            requestLocation()
        } else if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           viewModel.setErrorState(ErrorState.LOCATION_PERMISSION_DENIED)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkLocationEnable(this)

        if (!this.isConnected()) {
            viewModel.setErrorState(ErrorState.NETWORK_DISABLED)
        }

       if (viewModel.homeStateUI.errorState == null) {
           lifecycleScope.launch {
               viewModel.setLoading(true)
               delay(1000)
               requestPermissionLocale()
           }

       }
    }


    private fun requestLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    viewModel.setLocalization(latitude, longitude)
                    viewModel.getMeteorologicalData()
                    getCityNameFromCoordinates(latitude, longitude)
                } else {
                    lifecycleScope.launch {
                        delay(1000)
                        viewModel.setErrorState(ErrorState.ERROR_GET_LOCATION)
                    }
                }
            }
    }

    private fun getCityNameFromCoordinates(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>?

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses.first().subAdminArea
                viewModel.setCurrentCityName(cityName)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }
}