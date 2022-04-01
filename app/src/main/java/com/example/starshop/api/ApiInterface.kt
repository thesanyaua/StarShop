package com.example.starshop.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("api/v1/rywrg01naid88")
    fun getAllListNike():Call<ArrayList<Item>>

    @GET("api/v1/rywrg01naid88?sheet=adidas")
    fun getListAdidas():Call<ArrayList<Item>>

    @GET("api/v1/rywrg01naid88?sheet=puma")
    fun getListPuma():Call<ArrayList<Item>>

    @GET("api/v1/rywrg01naid88?sheet=new_balance")
    fun getListNewBalance():Call<ArrayList<Item>>

    @POST("api/v1/rywrg01naid88?sheet=basket")
    fun setBasketList(@Body list: ArrayList<BasketItem>):Call<ArrayList<BasketItem>>

    @POST("api/v1/rywrg01naid88?sheet=basket")
    fun setInfoUser(@Body user:BasketItem):Call<BasketItem>



    companion object {
        fun getRetrofit():ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://sheetdb.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}