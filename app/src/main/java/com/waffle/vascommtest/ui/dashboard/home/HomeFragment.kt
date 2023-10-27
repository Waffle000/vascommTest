package com.waffle.vascommtest.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseFragment
import com.waffle.vascommtest.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding.ivHome1.bringToFront()
    }

    override fun observeData() {
    }

    override fun init() {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

}