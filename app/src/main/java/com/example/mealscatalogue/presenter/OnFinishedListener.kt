package com.example.mealscatalogue.presenter

interface OnFinishedListener<T> {

    fun success(data: T)

    fun failure(message: String?)
}