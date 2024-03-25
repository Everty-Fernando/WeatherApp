package br.estudo.everty.app_weather.utils.extensions

import kotlin.math.roundToInt

fun String.firstLetterUpperCase(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}

fun Double.toDegreeCelsius(): String {
    return "${this.roundToInt()}°"
}