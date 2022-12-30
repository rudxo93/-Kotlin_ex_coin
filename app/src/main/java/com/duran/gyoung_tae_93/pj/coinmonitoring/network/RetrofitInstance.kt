package com.duran.gyoung_tae_93.pj.coinmonitoring.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // API호출할 URL
    private const val BASE_URL = "https://api.bithumb.com/"

    // 사용할 수 있도록 setting
    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance() : Retrofit {
        return client
    }

}