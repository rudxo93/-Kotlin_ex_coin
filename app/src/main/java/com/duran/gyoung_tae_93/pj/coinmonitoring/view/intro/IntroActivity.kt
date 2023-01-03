package com.duran.gyoung_tae_93.pj.coinmonitoring.view.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.duran.gyoung_tae_93.pj.coinmonitoring.MainActivity
import com.duran.gyoung_tae_93.pj.coinmonitoring.databinding.ActivityIntroBinding
import timber.log.Timber

// Splash 화면 만들기
// handler -> 3초 뒤에 다른 액티비티 이동(MainActivity로 이동)
// https://developer.android.com/develop/ui/views/launch/splash-screen

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()
        viewModel.first.observe(this, Observer {

            if (it) {
                // 처음 접속하는 유저가 아님(true)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // 처음 접속하는 유저(false)
                binding.animationView.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }

        })

    }
}