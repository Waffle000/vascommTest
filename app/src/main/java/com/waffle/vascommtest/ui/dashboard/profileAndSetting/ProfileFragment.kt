package com.waffle.vascommtest.ui.dashboard.profileAndSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.R
import com.waffle.vascommtest.databinding.FragmentProfileBinding

class ProfileFragment(private val name : String) : BaseFragment() {
    private lateinit var binding : FragmentProfileBinding
    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun observeData() {

    }

    override fun init() {
        binding.tvNameProfile.text = name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }
}