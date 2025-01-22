package br.com.everty.shared.network

import retrofit2.Retrofit

class ApiClient(private val baseUrl: String) {
    val client: Retrofit by lazy {
        ApiBuilder(baseUrl).client
    }
}