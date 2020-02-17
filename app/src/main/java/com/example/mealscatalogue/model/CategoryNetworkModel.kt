package com.example.mealscatalogue.model

import com.example.mealscatalogue.network.MealClientFactory
import com.example.mealscatalogue.network.model.categorylist.Category
import com.example.mealscatalogue.network.model.categorylist.CategoryList
import com.example.mealscatalogue.presenter.OnFinishedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryNetworkModel(
    private val presenter: OnFinishedListener<List<Category>>
) {

    private val compositeDisposable = CompositeDisposable()

    fun downloadData() {
        compositeDisposable.add(
            MealClientFactory
                .mealClient
                .getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleNext, this::handleError)
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    private fun handleNext(categoryList: CategoryList) {
        val catList = categoryList.categories

        presenter.success(catList)
    }

    private fun handleError(throwable: Throwable) {
        presenter.failure(throwable.message)
    }
}