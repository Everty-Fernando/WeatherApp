package br.com.everty.home.home_data.di

import br.com.everty.home.home_data.repository.WeatherRepository
import br.com.everty.home.home_data.repository.WeatherRepositoryImpl
import br.com.everty.home.home_data.service.WeatherAPI
import br.com.everty.shared.network.ApiClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectHomeDataModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(dataModule))
}
private val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single { ApiClient(
       "https://api.openweathermap.org/data/3.0/"
    ).client.create(WeatherAPI::class.java) }
}