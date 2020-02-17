package com.example.mealscatalogue.network

import com.example.mealscatalogue.common.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MealClientFactory {

    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val mealClient: MealClient by lazy {
        retrofitInstance.create(MealClient::class.java)
    }
}