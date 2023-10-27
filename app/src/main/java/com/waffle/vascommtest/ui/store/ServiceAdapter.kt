package com.waffle.vascommtest.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waffle.vascommtest.databinding.ItemServiceBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class ServiceAdapter(): RecyclerView.Adapter<ServiceAdapter.ViewHolder>(){

    class ViewHolder(val binding : ItemServiceBinding) : RecyclerView.ViewHolder(binding.root)

    private val itemList = ArrayList<Service>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = itemList[position]
        binding.apply {
            tvTitleService.text = item.title
            val formatter: NumberFormat = DecimalFormat("#,###")
            tvPriceService.text = "Rp. ${formatter.format(item.price)}"
            tvLocationService.text = item.location
            tvLocationDetailService.text = item.detailLocation
        }
    }

    override fun getItemCount() = itemList.size

    fun setData(dataList: List<Service>) {
        with(this.itemList) {
            clear()
            addAll(dataList)
        }
        notifyDataSetChanged()
    }
}