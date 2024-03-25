package br.estudo.everty.app_weather.network

import retrofit2.Retrofit

class ApiClient(private val baseUrl: String) {
    val client: Retrofit by lazy {
        ApiBuilder(baseUrl).client
    }
}