package com.example.impacklink.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Emulator: use 10.0.2.2. Physical phone: replace with your PC IPv4 address, e.g. http://192.168.1.5/ImpackLink/api/
    private const val BASE_URL = "http://192.168.106.7/ImpackLink/api/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}