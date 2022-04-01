package com.example.starshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.starshop.R
import com.example.starshop.adapters.BasketAdapter
import com.example.starshop.api.BasketItem
import com.example.starshop.databinding.FragmentBasketBinding
import com.example.starshop.viewmodel.BasketViewModel


class BasketFragment : Fragment() {
    lateinit var binding: FragmentBasketBinding
    lateinit var model: BasketViewModel
    private val adapter = BasketAdapter()

    companion object {
        var listBasket = ArrayList<BasketItem>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(BasketViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.recyclerView.adapter = adapter

        adapter.sumOlList.observe(viewLifecycleOwner) {
            val price = "$it ₴"
            binding.allPrice.text = price
        }

        binding.addButton.setOnClickListener {
            model.setUser(
                BasketItem(
                    firstName = binding.name.text.toString(),
                    phoneNumber = binding.phoneNumber.text.toString(),
                    allSum = binding.allPrice.text.toString(),
                    brand = "---------------------",
                    id = "---------------------",
                    image = "---------------------",
                    nameItem = "---------------------",
                    price = "---------------------"
                )
            )
            Thread.sleep(500)
            model.setBasket(listBasket)
            findNavController().navigate(R.id.action_basketFragment_to_mainFragment)
        }

    }
}