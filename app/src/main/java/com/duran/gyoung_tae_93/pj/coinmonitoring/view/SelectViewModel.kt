package com.duran.gyoung_tae_93.pj.coinmonitoring.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duran.gyoung_tae_93.pj.coinmonitoring.dataModel.CurrentPrice
import com.duran.gyoung_tae_93.pj.coinmonitoring.dataModel.CurrentPriceResult
import com.duran.gyoung_tae_93.pj.coinmonitoring.datastore.MyDataStore
import com.duran.gyoung_tae_93.pj.coinmonitoring.db.entity.InterestCoinEntity
import com.duran.gyoung_tae_93.pj.coinmonitoring.repository.DBRepository
import com.duran.gyoung_tae_93.pj.coinmonitoring.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SelectViewModel : ViewModel() {

    private val networkRepository = NetworkRepository()
    private val dbRepository = DBRepository()

    private lateinit var currentPriceResultList: ArrayList<CurrentPriceResult>

    // 데이터변화를 관찰 LiveData
    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
        get() = _currentPriceResult

    fun getCurrentCoinList() = viewModelScope.launch {

        val result = networkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()

        for(coin in result.data) {

            try{
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)

                currentPriceResultList.add(currentPriceResult)

            } catch (e : java.lang.Exception) {
                Timber.d(e.toString())
            }
        }

        _currentPriceResult.value = currentPriceResultList

    }

    fun setUpFirstFlag() = viewModelScope.launch {
        MyDataStore().setupFirstData()
    }

    // DB에 데이터 저장
    // Dispatchers.IO ? -> https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
    fun saveSelectedCoinList(selectedCoinList: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {

        // 1. 전체 코인 데이터를 가져와서 -> OK
        for(coin in currentPriceResultList) {

            Timber.d(coin.toString())

            // 2. 내가 선택한 코인인지 아닌지 구분해서
            // 포함하면 ture / 포함하지 않으면 false
            val selected = selectedCoinList.contains(coin.coinName)

            val interestCoinEntity = InterestCoinEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded_24H,
                coin.coinInfo.acc_trade_value_24H,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            // 3. 저장
            interestCoinEntity.let {
                dbRepository.insertInterestCoinData(it)
            }

        }
    }

}