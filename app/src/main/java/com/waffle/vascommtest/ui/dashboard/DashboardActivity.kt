package com.waffle.vascommtest.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.databinding.ActivityDashboardBinding
import com.waffle.vascommtest.module.SharedPreferences
import com.waffle.vascommtest.ui.dashboard.home.HomeFragment
import com.waffle.vascommtest.ui.dashboard.profileAndSetting.ProfileAndSettingFragment
import com.waffle.vascommtest.ui.login.LoginActivity
import com.waffle.vascommtest.ui.store.StoreActivity
import com.waffle.vascommtest.utils.loadImage
import org.koin.android.ext.android.inject
import xyz.hasnat.sweettoast.SweetToast


class DashboardActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var binding : ActivityDashboardBinding
    private var savedInstanceStateLocal : Bundle? = null
    private val viewModel : DashboardViewModel by inject()
    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceStateLocal = savedInstanceState
        viewModel.getUserData(SharedPreferences(this).userId)
    }

    override fun observeData() {
        viewModel.apply {
            observeUserDataSuccess().observe(this@DashboardActivity) {
                it.getContentIfNotHandled()?.let {data ->
                    val header: View = binding.navView.getHeaderView(0)
                    val mNameTextView = header.findViewById<View>(R.id.tv_name_header) as TextView
                    mNameTextView.text = data.data?.name
                }
            }
        }
    }

    override fun init() {
        binding.apply {
            btnStore.setOnClickListener{
                startActivity(Intent(this@DashboardActivity, StoreActivity::class.java))
            }
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            navView.setNavigationItemSelectedListener(this@DashboardActivity)
            val toggle = ActionBarDrawerToggle(this@DashboardActivity, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            if (savedInstanceStateLocal == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
                navView.setCheckedItem(R.id.nav_home)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val header: View = binding.navView.getHeaderView(0)
        val mNameTextView = header.findViewById<View>(R.id.tv_name_header) as TextView
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_profile -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileAndSettingFragment(mNameTextView.text.toString())).commit()
            R.id.nav_logout -> {
                SharedPreferences(this).resetSharedPref()
                SweetToast.success(this, "Berhasil Logout")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}