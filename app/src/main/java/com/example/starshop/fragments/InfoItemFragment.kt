package com.example.starshop.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starshop.R
import com.example.starshop.databinding.FragmentInfoItemBinding
import com.example.starshop.viewmodel.InfoItemViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class InfoItemFragment : Fragment() {
    private lateinit var binding: FragmentInfoItemBinding
    private lateinit var model: InfoItemViewModel
    private var id: String = ""
    private var name: String = ""
    private var photo: String = ""
    private var descriptionInfo: String = ""
    private var price: String = ""
    private var brand: String = ""
    private var priceInBasket:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id", "")
            name = it.getString("name", "")
            photo = it.getString("photo", "")
            descriptionInfo = it.getString("descriptionInfo", "")
            brand = it.getString("brand", "")
            price = it.getString("price", "") + " ₴"
            priceInBasket = it.getString("price", "")

        }
        model = ViewModelProvider(this).get(InfoItemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameProduct.text = name
        binding.infoProduct.text = descriptionInfo
        binding.price.text = price
        iconBrand(brand)
        Picasso.get().load(photo).into(binding.imageItem)

        binding.toolbar.setNavigationIcon(R.drawable.back_icon)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.addButton.setOnClickListener {
            model.setInBasket("", brand, "", id, photo, "", name, priceInBasket)
            binding.infoAddBasket.text = "ТОВАР ДОДАНО ДО КОШИКУ"
        }
    }

    private fun iconBrand(brand: String?) {
        when (brand) {
            "nike" -> binding.brandLogo.setImageResource(R.drawable.nike)
            "adidas" -> binding.brandLogo.setImageResource(R.drawable.adidas)
            "puma" -> binding.brandLogo.setImageResource(R.drawable.puma)
            "new_balance" -> binding.brandLogo.setImageResource(R.drawable.new_balance)
        }
    }

}