package com.waffle.vascommtest.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.data.request.AuthRequest
import com.waffle.vascommtest.databinding.ActivityLoginBinding
import com.waffle.vascommtest.module.SharedPreferences
import com.waffle.vascommtest.utils.disableScreen
import com.waffle.vascommtest.utils.enableScreen
import org.koin.android.ext.android.inject
import xyz.hasnat.sweettoast.SweetToast

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by inject()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeData() {
        viewModel.apply {
            observeLoginSuccess().observe(this@LoginActivity) {
                it.getContentIfNotHandled()?.let{data ->
                    hideLoading()
                    SharedPreferences(this@LoginActivity).apply {
                        isLogin = true
                        userId = if(data.length == 17) {
                            data.takeLast(1)
                        } else {
                            data.takeLast(2)
                        }
                        userToken = data
                    }
                }
            }
        }
    }

    override fun init() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (etEmailLogin.text.toString().isBlank()) {
                    SweetToast.error(this@LoginActivity, "Email is still empty")
                } else if (etPasswordLogin.text.toString().isBlank()) {
                    SweetToast.error(this@LoginActivity, "Password is still empty")
                } else {
                    showLoading()
                    val data = AuthRequest(email = etEmailLogin.text.toString(), password = etPasswordLogin.text.toString())
                    viewModel.postLogin(data)
                }
            }
        }
    }

    private fun showLoading(){
        disableScreen()
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        enableScreen()
        binding.pbLoading.visibility = View.GONE
    }
}