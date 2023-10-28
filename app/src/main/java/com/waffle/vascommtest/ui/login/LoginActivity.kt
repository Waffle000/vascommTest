package com.waffle.vascommtest.ui.login

import android.content.Intent
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
import com.waffle.vascommtest.ui.dashboard.DashboardActivity
import com.waffle.vascommtest.ui.register.RegisterActivity
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
                        userId = if(data.token?.length == 17) {
                            data.token?.takeLast(1)?.toInt() ?: 0
                        } else {
                            data.token?.takeLast(2)?.toInt() ?: 0
                        }
                        userToken = data.token ?: ""
                    }
                    startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
                }
            }

            observeSingleError().observe(this@LoginActivity) {
                it.getContentIfNotHandled()?.let { error ->
                    hideLoading()
                    SweetToast.error(this@LoginActivity, error.msg)
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
            btnToRegister.setOnClickListener{
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
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