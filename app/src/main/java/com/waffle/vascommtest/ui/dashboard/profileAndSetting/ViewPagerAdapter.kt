package com.waffle.vascommtest.ui.dashboard.profileAndSetting

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val name: String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SettingFragment()
        } else ProfileFragment(name)
    }

    override fun getItemCount(): Int {
        return 2
    }
}