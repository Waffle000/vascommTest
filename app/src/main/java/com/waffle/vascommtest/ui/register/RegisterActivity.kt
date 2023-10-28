package com.waffle.vascommtest.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.data.request.AuthRequest
import com.waffle.vascommtest.databinding.ActivityRegisterBinding
import com.waffle.vascommtest.module.SharedPreferences
import com.waffle.vascommtest.ui.dashboard.DashboardActivity
import com.waffle.vascommtest.ui.login.LoginActivity
import com.waffle.vascommtest.utils.disableScreen
import com.waffle.vascommtest.utils.enableScreen
import org.koin.android.ext.android.inject
import xyz.hasnat.sweettoast.SweetToast

class RegisterActivity : BaseActivity() {
    private lateinit var binding : ActivityRegisterBinding

    private val viewModel : RegisterViewModel by inject()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun observeData() {
        viewModel.apply {
            observeRegisterSuccess().observe(this@RegisterActivity){
                it.getContentIfNotHandled()?.let { data->
                    hideLoading()
                    SharedPreferences(this@RegisterActivity).apply {
                        isLogin = true
                        userId = data.id ?: 0
                        userToken = data.token ?: ""
                    }
                    startActivity(Intent(this@RegisterActivity, DashboardActivity::class.java))
                }
            }

            observeSingleError().observe(this@RegisterActivity) {
                it.getContentIfNotHandled()?.let { error ->
                    hideLoading()
                    SweetToast.error(this@RegisterActivity, error.msg)
                }
            }
        }
    }

    override fun init() {
        binding.apply {
            btnRegister.setOnClickListener {
                when{
                    etFirstNameRegister.toString().toString().isBlank() -> SweetToast.error(this@RegisterActivity, "First name is still empty")
                    etLastNameRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Last name is still empty")
                    etKtpRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Your NIK is still empty")
                    etEmailRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Email is still empty")
                    etTelpRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Your phone number is still empty")
                    etPasswordRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Password is still empty")
                    etKonfirmPasswordRegister.text.toString().isBlank() -> SweetToast.error(this@RegisterActivity, "Confirmation password is still empty")
                    else -> {
                        showLoading()
                        val data = AuthRequest(email = etEmailRegister.text.toString(), password = etPasswordRegister.text.toString())
                        viewModel.postRegister(data)
                    }
                }
            }
            btnToLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
    private fun showLoading() {
        disableScreen()
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        enableScreen()
        binding.pbLoading.visibility = View.GONE
    }
}