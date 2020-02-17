package com.example.mealscatalogue.view

interface MealsCatalogueViewInterface<T> {

    fun hideProgressBar()

    fun showData(categoriesList: T)

    fun showError(message: String?)
}