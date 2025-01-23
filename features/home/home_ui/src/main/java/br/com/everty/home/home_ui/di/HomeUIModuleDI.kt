package br.com.everty.home.home_ui.di

import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.home.home_ui.ui.viewmodel.HomeViewModel
import br.com.everty.shared.utils.helpers.LocationHelper
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectHomeUIModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(uiModule))
}

private val uiModule = module {
    factory { (initialState: HomeStateUI) ->
        HomeViewModel(
            application = get(),
            getMeteorologicalDataUseCase = get(),
            locationHelper = get(),
            initialState = initialState
        )
    }

    factory {
       LocationHelper(
            androidContext(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }
}