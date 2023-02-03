package com.duran.gyoung_tae_93.pj.coinmonitoring.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duran.gyoung_tae_93.pj.coinmonitoring.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("onCreate")
    }
}