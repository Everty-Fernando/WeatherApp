package br.estudo.everty.app_weather.home.di

import br.com.everty.shared.utils.helpers.LocationHelper
import br.estudo.everty.app_weather.home.ui.screens.viewmodel.HomeViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectHomeModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(uiModule))
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