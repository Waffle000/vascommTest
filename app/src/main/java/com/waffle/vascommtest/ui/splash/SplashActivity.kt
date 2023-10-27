package com.waffle.vascommtest.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.ui.dashboard.DashboardActivity

class SplashActivity : BaseActivity() {
    override fun onViewCreated(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }

    override fun observeData() {

    }

    override fun init() {

    }
}