package br.com.everty.shared.utils.extensions

import android.content.Context
import android.content.Intent
import android.provider.Settings

fun redirectToAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = android.net.Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
}

fun openLocationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    context.startActivity(intent)
}