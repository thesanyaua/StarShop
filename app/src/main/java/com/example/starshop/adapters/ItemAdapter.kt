package com.example.starshop.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.starshop.R
import com.example.starshop.api.Item
import com.example.starshop.databinding.ItemBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder>() {
    private lateinit var binding:ItemBinding
    var list = ArrayList<Item>()

    class ItemAdapterViewHolder(val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item, parent, false)
        return ItemAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapterViewHolder, position: Int) {
        val productItem = list[position]
        val price = productItem.price + " ГРН"
        holder.binding.nameProduct.text = productItem.name
        holder.binding.infoProduct.text = productItem.shotDescriptionInfo
        holder.binding.price.text = price
        Picasso.get().load(productItem.photo).into(binding.imageProduct)
        //Image Radius
        holder.binding.imageProduct.clipToOutline = true
        //Click Info
        binding.buttonInfoItem.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_infoItemFragment,
        setBundle(productItem.id, productItem.photo, productItem.name, productItem.descriptionInfo, productItem.price, productItem.brand)) }
        //icon brands
        iconBrand(productItem.brand)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(listItem:ArrayList<Item>) {
        list = listItem
        notifyDataSetChanged()
    }

    private fun setBundle(id:String, photo:String, name:String, descriptionInfo:String, price:String, brand:String):Bundle {
        val bun = Bundle()
        bun.putString("photo", photo);
        bun.putString("name", name);
        bun.putString("descriptionInfo", descriptionInfo);
        bun.putString("price", price);
        bun.putString("id", id);
        bun.putString("brand", brand)
        return bun
    }

    private fun iconBrand(brand:String) {
        when(brand) {
            "nike" -> binding.brandIcon.setImageResource(R.drawable.nike)
            "adidas" -> binding.brandIcon.setImageResource(R.drawable.adidas)
            "puma" -> binding.brandIcon.setImageResource(R.drawable.puma)
            "new_balance" -> binding.brandIcon.setImageResource(R.drawable.new_balance)
        }
    }
}