package com.dmitri.yandex_tasks.network.retrofit

import com.dmitri.yandex_tasks.network.Constant
import com.dmitri.yandex_tasks.network.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val TIMEOUT = 15

    private val httpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor(Constant.AUTH_TOKEN))
            .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TodoApi by lazy {
        retrofit.create(TodoApi::class.java)
    }
}