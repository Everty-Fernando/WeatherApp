package br.estudo.everty.app_weather.app.di

import br.com.everty.home.home_data.di.injectHomeDataModule
import br.com.everty.home.home_domain.di.injectHomeDomainModule


object HomeModuleInit {
    fun initHomeModule() {
        injectHomeDataModule()
        injectHomeDomainModule()
    }
}