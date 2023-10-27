package com.waffle.vascommtest.ui.dashboard.profileAndSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseFragment

class SettingFragment: BaseFragment() {
    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun observeData() {

    }

    override fun init() {

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }
}