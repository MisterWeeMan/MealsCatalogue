package com.example.mealscatalogue.model

import com.example.mealscatalogue.network.MealClientFactory
import com.example.mealscatalogue.network.model.category.Meal
import com.example.mealscatalogue.network.model.category.MealList
import com.example.mealscatalogue.presenter.OnFinishedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealNetworkModel(
    private val presenter: OnFinishedListener<List<Meal>>
) {

    private val compositeDisposable = CompositeDisposable()

    fun downloadData(category: String) {
        compositeDisposable.add(
            MealClientFactory
                .mealClient
                .getMealsByCategoryName(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleNext, this::handleError)
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun handleNext(mealModel: MealList) {
        val mealList = mealModel.meals

        presenter.success(mealList)
    }

    private fun handleError(throwable: Throwable) {
        presenter.failure(throwable.message)
    }
}