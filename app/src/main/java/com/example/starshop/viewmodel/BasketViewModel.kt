package com.example.starshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starshop.adapters.BasketAdapter
import com.example.starshop.api.ApiInterface
import com.example.starshop.api.BasketItem
import com.example.starshop.fragments.BasketFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketViewModel : ViewModel() {


    fun setUser(user: BasketItem) {
        val setR = ApiInterface.getRetrofit().setInfoUser(user)

        setR.enqueue(object : Callback<BasketItem?> {
            override fun onResponse(call: Call<BasketItem?>, response: Response<BasketItem?>) {
                response.body()
            }

            override fun onFailure(call: Call<BasketItem?>, t: Throwable) {

            }
        })
    }

    fun setBasket(list: ArrayList<BasketItem>) {
        val setR = ApiInterface.getRetrofit().setBasketList(list)

        setR.enqueue(object : Callback<ArrayList<BasketItem>?> {
            override fun onResponse(
                call: Call<ArrayList<BasketItem>?>,
                response: Response<ArrayList<BasketItem>?>
            ) {
                response.body()
            }

            override fun onFailure(call: Call<ArrayList<BasketItem>?>, t: Throwable) {

            }
        })

    }
}