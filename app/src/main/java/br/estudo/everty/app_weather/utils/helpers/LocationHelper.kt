package br.estudo.everty.app_weather.utils.helpers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import java.util.Locale

class LocationHelper(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient
) {
    private val permissions =  arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun checkAndRequestPermissionLocale(
        activity: Activity,
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        val deniedPermissions = permissions.filter { permission ->
            ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
        }

        when {
            deniedPermissions.isEmpty() -> { onPermissionGranted() }

            deniedPermissions.any { permission ->
                ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
            } -> {
                onPermissionDenied()
            }

            else -> {
                ActivityCompat.requestPermissions(activity, deniedPermissions.toTypedArray(), REQUEST_LOCATION_PERMISSION)
            }
        }
    }

    fun getLastKnownLocation(
        onLocationRetrieved: (Location) -> Unit,
        onError: () -> Unit
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    onLocationRetrieved(location)
                } else {
                    onError()
                }
            }
    }

    fun getCityName(
        latitude: Double,
        longitude: Double,
        onCityNameRetrieved: (String) -> Unit
    ) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val cityName = addresses.first().subAdminArea
                onCityNameRetrieved(cityName)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }
}
