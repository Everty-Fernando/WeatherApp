package br.estudo.everty.app_weather.utils.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat

open class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}