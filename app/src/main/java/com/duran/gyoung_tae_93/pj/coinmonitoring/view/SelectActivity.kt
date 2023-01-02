package com.duran.gyoung_tae_93.pj.coinmonitoring.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.gyoung_tae_93.pj.coinmonitoring.MainActivity
import com.duran.gyoung_tae_93.pj.coinmonitoring.R
import com.duran.gyoung_tae_93.pj.coinmonitoring.databinding.ActivitySelectBinding
import com.duran.gyoung_tae_93.pj.coinmonitoring.view.adapter.SelectRVAdapter
import timber.log.Timber

// 가상화폐 현재가 조회
// https://apidocs.bithumb.com/reference/%ED%98%84%EC%9E%AC%EA%B0%80-%EC%A0%95%EB%B3%B4-%EC%A1%B0%ED%9A%8C-all

class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding

    private val viewModel : SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater, )
        setContentView(binding.root)

        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this, Observer {

            selectRVAdapter = SelectRVAdapter(this, it)
            // binding
            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

            Timber.d(it.toString())
        })

        binding.laterTextArea.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}