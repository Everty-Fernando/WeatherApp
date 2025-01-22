package br.estudo.everty.app_weather.home.di

import br.com.everty.shared.network.ApiClient
import br.com.everty.shared.utils.helpers.LocationHelper
import br.estudo.everty.app_weather.home.data.repository.WeatherRepository
import br.estudo.everty.app_weather.home.data.repository.WeatherRepositoryImpl
import br.estudo.everty.app_weather.home.data.service.WeatherAPI
import br.estudo.everty.app_weather.home.domain.usecase.GetMeteorologicalDataUseCase
import br.estudo.everty.app_weather.home.domain.mappers.MeteorologicalDataUIMapper
import br.estudo.everty.app_weather.home.domain.mappers.SummaryUIMapper
import br.estudo.everty.app_weather.home.domain.mappers.WeatherDataDailyUIMapper
import br.estudo.everty.app_weather.home.domain.mappers.WeatherDataHoursUIMapper
import br.estudo.everty.app_weather.home.domain.mappers.WeatherDataUIMapper
import br.estudo.everty.app_weather.home.domain.mappers.WeatherImageUIMapper
import br.estudo.everty.app_weather.home.ui.screens.viewmodel.HomeViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectHomeModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(dataModule, domainModule, uiModule))
}
private val dataModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single { ApiClient(
       "https://api.openweathermap.org/data/3.0/"
    ).client.create(WeatherAPI::class.java) }
}

private val domainModule = module {
    factory { GetMeteorologicalDataUseCase(get(), get()) }
    factory { MeteorologicalDataUIMapper(get(), get(), get()) }
    factory { WeatherDataUIMapper(get(), get()) }
    factory { WeatherDataHoursUIMapper(get()) }
    factory { WeatherDataDailyUIMapper(get()) }
    factory { WeatherImageUIMapper() }
    factory { SummaryUIMapper(androidContext()) }
}

private val uiModule = module {
    factory { HomeViewModel(get(), get(), get()) }
    factory {
       LocationHelper(
            androidContext(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }
}