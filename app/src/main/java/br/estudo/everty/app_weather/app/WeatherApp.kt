package br.estudo.everty.app_weather.app

import android.app.Application
import br.estudo.everty.app_weather.home.di.injectHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
        }
        injectHomeModule()
    }
}