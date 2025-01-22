package br.estudo.everty.app_weather.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun ViewModel.runAfter(
    delayTimeMillis: Long = 1000L,
    runAfter: () -> Unit = {}
) {
    viewModelScope.launch {
        delay(delayTimeMillis)
        runAfter()
    }
}