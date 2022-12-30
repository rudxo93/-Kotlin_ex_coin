package com.duran.gyoung_tae_93.pj.coinmonitoring.repository

import com.duran.gyoung_tae_93.pj.coinmonitoring.network.Api
import com.duran.gyoung_tae_93.pj.coinmonitoring.network.RetrofitInstance
import retrofit2.create

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

}