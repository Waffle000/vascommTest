package com.waffle.vascommtest.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.databinding.ActivityRegisterBinding
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity() {
    private lateinit var binding : ActivityRegisterBinding

    private val viewModel : RegisterViewModel by inject()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }

    override fun init() {
        TODO("Not yet implemented")
    }
}