package br.com.everty.shared.utils.di

import br.com.everty.shared.utils.helpers.LocationHelper
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectUtilsModule() = loadStartupUIModule

private val loadStartupUIModule by lazy {
    loadKoinModules(listOf(utilsModule))
}

private val utilsModule = module {
    factory {
       LocationHelper(
            androidContext(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    }
}