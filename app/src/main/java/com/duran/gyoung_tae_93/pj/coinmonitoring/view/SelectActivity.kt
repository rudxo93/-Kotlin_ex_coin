package com.duran.gyoung_tae_93.pj.coinmonitoring.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.gyoung_tae_93.pj.coinmonitoring.MainActivity
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

            // 처음 유저인지 아닌지 구분
            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)

        }

        /*
            만약 코인이 100만개라면 어떻게 할꺼임?
            메인에서 저장된 코인을 이용하는 로직이 있을수도 있는데 그럼 10만개만 저장이 되었는데 나머지 90만개는
            저장이 안됐는데 그럼 원하는 형태로 로직 구현이 안될텐데????
            -> 100만개를 다 저장되고 난 다음에 MainActivity로 넘겨주는 것을 해야한다!
            */
        viewModel.save.observe(this, Observer {
            if(it.equals("done")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        })

    }

}