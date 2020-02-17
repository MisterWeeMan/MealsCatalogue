package com.example.mealscatalogue.network

import com.example.mealscatalogue.common.CATEGORIES_ENDPOINT
import com.example.mealscatalogue.common.MEALS_ENDPOINT
import com.example.mealscatalogue.network.model.category.MealList
import com.example.mealscatalogue.network.model.categorylist.CategoryList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MealClient {

    @GET(CATEGORIES_ENDPOINT)
    fun getCategories(): Observable<CategoryList>

    @GET(MEALS_ENDPOINT)
    fun getMealsByCategoryName(@Query("c") category: String): Observable<MealList>
}