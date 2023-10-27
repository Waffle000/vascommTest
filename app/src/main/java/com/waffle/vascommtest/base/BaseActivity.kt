package com.waffle.vascommtest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewCreated(savedInstanceState)
        observeData()
        init()
    }

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)

    protected abstract fun observeData()

    protected abstract fun init()
}