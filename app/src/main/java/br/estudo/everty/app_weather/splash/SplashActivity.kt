package br.estudo.everty.app_weather.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.estudo.everty.app_weather.home.ui.HomeActivity
import br.estudo.everty.app_weather.utils.activity.BaseActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWeatherTheme {
                SplashScreen(onNavigateToHome = ::navigateToHome)
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}