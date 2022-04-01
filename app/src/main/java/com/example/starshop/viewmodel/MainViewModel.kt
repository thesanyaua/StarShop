package com.example.starshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starshop.adapters.ItemAdapter
import com.example.starshop.api.ApiInterface
import com.example.starshop.api.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listItem = MutableLiveData<ArrayList<Item>>()

    init {
        addListNike()
    }

    fun addListNike() {
        val getR = ApiInterface.getRetrofit().getAllListNike()

        getR.enqueue(object : Callback<ArrayList<Item>?> {
            override fun onResponse(
                call: Call<ArrayList<Item>?>,
                response: Response<ArrayList<Item>?>
            ) {
                listItem.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Item>?>, t: Throwable) {

            }
        })
    }

    fun addListAdidas() {
        val getR = ApiInterface.getRetrofit().getListAdidas()

        getR.enqueue(object : Callback<ArrayList<Item>?> {
            override fun onResponse(
                call: Call<ArrayList<Item>?>,
                response: Response<ArrayList<Item>?>
            ) {
                listItem.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Item>?>, t: Throwable) {

            }
        })
    }

    fun addListPuma() {
        val getR = ApiInterface.getRetrofit().getListPuma()

        getR.enqueue(object : Callback<ArrayList<Item>?> {
            override fun onResponse(
                call: Call<ArrayList<Item>?>,
                response: Response<ArrayList<Item>?>
            ) {
                listItem.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Item>?>, t: Throwable) {

            }
        })
    }

    fun addListNewBalance() {
        val gerR = ApiInterface.getRetrofit().getListNewBalance()

        gerR.enqueue(object : Callback<ArrayList<Item>?> {
            override fun onResponse(
                call: Call<ArrayList<Item>?>,
                response: Response<ArrayList<Item>?>
            ) {
                listItem.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Item>?>, t: Throwable) {

            }
        })
    }

    fun cleanAdapter(adapter:ItemAdapter) {
        val list = ArrayList<Item>()
        adapter.updateList(list)
    }
}