package com.example.mealscatalogue.presenter.meal

interface MealPresenterInterface {

    fun getMealListByCategory(category: String)

    fun onDestroyCalled()
}