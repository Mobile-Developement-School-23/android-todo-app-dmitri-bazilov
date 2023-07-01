package com.dmitri.yandex_tasks.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {

    private val HEADER_NAME = "Authorization"
    private val TOKEN_TYPE = "Bearer"

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain
            .request()
            .newBuilder()
            .addHeader(HEADER_NAME, "$TOKEN_TYPE $authToken")
            .build()
        return chain.proceed(req)
    }
}