package com.duran.gyoung_tae_93.pj.coinmonitoring.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.duran.gyoung_tae_93.pj.coinmonitoring.R
import com.duran.gyoung_tae_93.pj.coinmonitoring.databinding.ActivitySelectBinding
import timber.log.Timber

// 가상화폐 현재가 조회
// https://apidocs.bithumb.com/reference/%ED%98%84%EC%9E%AC%EA%B0%80-%EC%A0%95%EB%B3%B4-%EC%A1%B0%ED%9A%8C-all

class SelectActivity : AppCompatActivity() {

    private val viewModel : SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {
            Timber.d(it.toString())
        })

    }

}