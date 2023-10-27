package com.waffle.vascommtest.ui.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.waffle.vascommtest.R
import com.waffle.vascommtest.base.BaseActivity
import com.waffle.vascommtest.databinding.ActivityStoreBinding

class StoreActivity : BaseActivity() {
    private lateinit var binding: ActivityStoreBinding

    private lateinit var productAdapter: ProductAdapter

    private lateinit var serviceAdapter: ServiceAdapter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productAdapter = ProductAdapter()
        serviceAdapter = ServiceAdapter()
    }

    override fun observeData() {
        val productList = listOf(
            Product(4, "Suntik Steril", 100000.0),
            Product(4, "Suntik Steril", 100000.0),
            Product(4, "Suntik Steril", 100000.0),
            Product(4, "Suntik Steril", 100000.0),
            Product(4, "Suntik Steril", 100000.0)
        )

        val serviceList = listOf(
            Service("PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja", 1400000.0, "Lenmark Surabaya", "Dukuh Pakis, Surabaya"),
            Service("PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja", 1400000.0, "Lenmark Surabaya", "Dukuh Pakis, Surabaya"),
            Service("PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja", 1400000.0, "Lenmark Surabaya", "Dukuh Pakis, Surabaya"),
            Service("PCR Swab Test (Drive Thru) Hasil 1 Hari Kerja", 1400000.0, "Lenmark Surabaya", "Dukuh Pakis, Surabaya")
        )

        productAdapter.setData(productList)
        serviceAdapter.setData(serviceList)
    }

    override fun init() {
        binding.apply {
            rvProduct.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(this@StoreActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            rvService.apply {
                adapter = serviceAdapter
                layoutManager = LinearLayoutManager(this@StoreActivity)
            }
        }
    }
}