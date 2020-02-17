package com.example.mealscatalogue.network.model.categorylist


import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("categories") val categories: List<Category>
)