package br.estudo.everty.app_weather.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.estudo.everty.app_weather.home.ui.HomeActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object LocationUtils {
    private const val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun requestPermissionLocale(activity: Activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        if (ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permissão concedida, podemos solicitar a localização
            requestLocation()
        } else {
            // Solicitar permissão de localização
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun requestLocation(): Pair<Double, Double> {
        var latLong = Pair(0.0, 0.0)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Aqui você recebe a localização do usuário
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    latLong =  Pair(latitude, longitude)
                }
            }.addOnFailureListener {
                Log.d("Error", it.message.toString())
            }

        return latLong
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}