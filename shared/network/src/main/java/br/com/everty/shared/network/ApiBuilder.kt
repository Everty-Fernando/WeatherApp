package br.com.everty.shared.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.google.gson.Gson

class ApiBuilder(private val url: String) {

    val gson: Gson? =
        GsonBuilder()
            .setLenient()
            .create()

    val client: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val okHttpBuilder by lazy {
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }


    companion object {
        private const val TIMEOUT = 20L
    }
}