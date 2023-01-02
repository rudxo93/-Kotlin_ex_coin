package com.duran.gyoung_tae_93.pj.coinmonitoring.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.duran.gyoung_tae_93.pj.coinmonitoring.R
import timber.log.Timber

// Splash 화면 만들기
// handler -> 3초 뒤에 다른 액티비티 이동(MainActivity로 이동)
// https://developer.android.com/develop/ui/views/launch/splash-screen

class IntroActivity : AppCompatActivity() {

    private val viewModel : IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()

    }
}