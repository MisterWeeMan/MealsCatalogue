package com.example.mealscatalogue.network.model.category


import com.google.gson.annotations.SerializedName

data class MealList(
    @SerializedName("meals") val meals: List<Meal>
)