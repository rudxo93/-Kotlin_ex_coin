package com.duran.gyoung_tae_93.pj.coinmonitoring.network

import com.duran.gyoung_tae_93.pj.coinmonitoring.network.model.CurrentPriceList
import retrofit2.http.GET

interface Api {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList

}