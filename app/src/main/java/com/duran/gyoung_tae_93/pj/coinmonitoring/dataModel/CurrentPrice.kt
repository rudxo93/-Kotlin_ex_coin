package com.duran.gyoung_tae_93.pj.coinmonitoring.dataModel

data class CurrentPrice(

    val opening_price : String,
    val closing_price : String,
    val min_price : String,
    val max_price : String,
    val units_traded : String,
    val acc_trade_value : String,
    val prev_closing_price : String,
    val units_traded_24H : String,
    val acc_trade_value_24H : String,
    val fluctate_24H : String,
    val fluctate_rate_24H : String

    /*
    opening_price: 20777000,
    closing_price: 21046000,
    min_price: 20773000,
    max_price: 21104000,
    units_traded: 1205.95543318,
    acc_trade_value: 25317040614.1734,
    prev_closing_price: 20775000,
    units_traded_24H: 1871.7286668,
    acc_trade_value_24H: 39211142592.7425,
    fluctate_24H: 198000,
    fluctate_rate_24H: 0.95
     */

)
