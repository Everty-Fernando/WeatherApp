package br.estudo.everty.app_weather.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isConnected(): Boolean {

    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        val hasWifiTransport = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        val hasCellularTransport = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        val hasInternetCapability = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val hasVPNTransport = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)

        return hasInternetCapability || hasCellularTransport || hasWifiTransport || hasVPNTransport

    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }


}