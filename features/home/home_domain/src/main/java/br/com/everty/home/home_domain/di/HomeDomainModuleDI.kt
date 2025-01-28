package br.com.everty.home.home_domain.di

import br.com.everty.home.home_domain.mappers.MeteorologicalDataUIMapper
import br.com.everty.home.home_domain.mappers.SummaryUIMapper
import br.com.everty.home.home_domain.mappers.WeatherDataDailyUIMapper
import br.com.everty.home.home_domain.mappers.WeatherDataHoursUIMapper
import br.com.everty.home.home_domain.mappers.WeatherDataUIMapper
import br.com.everty.home.home_domain.mappers.WeatherImageUIMapper
import br.com.everty.home.home_domain.usecase.GetMeteorologicalDataUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectHomeDomainModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(domainModule))
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
