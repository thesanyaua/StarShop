package com.example.starshop.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.starshop.api.BasketItem
import com.example.starshop.fragments.BasketFragment

class InfoItemViewModel : ViewModel() {


    fun setInBasket(
        allSum: String, brand: String, firstName: String,
        id: String, image: String, phoneNumber: String, nameItem: String, price: String
    ) {
        BasketFragment.listBasket
            .add(BasketItem(allSum, brand, firstName, id, image, phoneNumber, nameItem, price))
    }
}