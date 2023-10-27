package com.waffle.vascommtest.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waffle.vascommtest.databinding.ItemProductBinding
import com.waffle.vascommtest.databinding.ItemServiceBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    private val itemList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemProductBinding.inflate(
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
            tvStarProduct.text = item.star.toString()
            tvTitleProduct.text = item.title
            val formatter: NumberFormat = DecimalFormat("#,###")
            tvPriceProduct.text = "Rp. ${formatter.format(item.price)}"
        }
    }

    override fun getItemCount() = itemList.size

    fun setData(dataList: List<Product>) {
        with(this.itemList) {
            clear()
            addAll(dataList)
        }
        notifyDataSetChanged()
    }
}