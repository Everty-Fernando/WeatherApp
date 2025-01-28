package br.com.everty.home.home_ui.di

import br.com.everty.home.home_ui.state.HomeStateUI
import br.com.everty.home.home_ui.ui.viewmodel.HomeViewModel
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
}