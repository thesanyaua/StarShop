package com.example.starshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.starshop.R
import com.example.starshop.api.BasketItem
import com.example.starshop.databinding.BasketBinding
import com.example.starshop.fragments.BasketFragment
import com.squareup.picasso.Picasso

class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketAdapterViewHolder>() {
    private lateinit var binding: BasketBinding
    private var listBasket = BasketFragment.listBasket
    var sumOlList = MutableLiveData<Int>()

    init {
        sumList()
    }

    class BasketAdapterViewHolder(val binding: BasketBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.basket,
            parent,
            false
        )
        return BasketAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        val basketItem = listBasket[position]
        holder.binding.nameItem.text = basketItem.nameItem
        holder.binding.price.text = basketItem.price
        Picasso.get().load(basketItem.image).into(binding.imageItem)
        iconBrand(basketItem.brand)
        //Del item
        holder.binding.del.setOnClickListener {
            deleteItem(holder.adapterPosition)
            sumList()
        }
    }

    override fun getItemCount(): Int {
        return listBasket.size
    }

    private fun iconBrand(brand: String) {
        when (brand) {
            "nike" -> binding.brandItem.setImageResource(R.drawable.nike)
            "adidas" -> binding.brandItem.setImageResource(R.drawable.adidas)
            "puma" -> binding.brandItem.setImageResource(R.drawable.puma)
            "new_balance" -> binding.brandItem.setImageResource(R.drawable.new_balance)
        }
    }

    private fun sumList() {
        var sum = 0
        listBasket.forEach {
            sum += it.price.toInt()
        }
        sumOlList.postValue(sum)
    }

    private fun deleteItem(position: Int) {
        listBasket.removeAt(position)
        notifyItemRemoved(position)
    }
}