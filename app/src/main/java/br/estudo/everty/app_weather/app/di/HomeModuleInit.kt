package br.estudo.everty.app_weather.app.di

import br.com.everty.home.home_data.di.injectHomeDataModule


object HomeModuleInit {
    fun initHomeModule() {
        injectHomeDataModule()
    }
}