package com.waffle.vascommtest.ui.dashboard.profileAndSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.databinding.FragmentProfileAndSettingBinding


class ProfileAndSettingFragment(private val name: String) : BaseFragment() {

    private lateinit var binding : FragmentProfileAndSettingBinding

    private lateinit var adapter: ViewPagerAdapter

    override fun onViewCreated(savedInstanceState: Bundle?) {
    }

    override fun observeData() {
    }

    override fun init() {
        binding.apply {
            tlProfileAndSetting.addTab(tlProfileAndSetting.newTab().setText("Profile"))
            tlProfileAndSetting.addTab(tlProfileAndSetting.newTab().setText("Setting"))
            val fragmentManager: FragmentManager = childFragmentManager
            adapter = ViewPagerAdapter(fragmentManager, lifecycle, name)
            vpProfileAndSetting.adapter = adapter
            tlProfileAndSetting.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    vpProfileAndSetting.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
            vpProfileAndSetting.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    tlProfileAndSetting.selectTab(tlProfileAndSetting.getTabAt(position))
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileAndSettingBinding.inflate(inflater)
        return binding.root
    }
}