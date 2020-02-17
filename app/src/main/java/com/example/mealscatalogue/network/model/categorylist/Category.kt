package com.example.mealscatalogue.network.model.categorylist


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryDescription") val strCategoryDescription: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String
)