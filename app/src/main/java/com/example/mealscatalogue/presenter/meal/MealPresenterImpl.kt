package com.example.mealscatalogue.presenter.meal

import com.example.mealscatalogue.model.MealNetworkModel
import com.example.mealscatalogue.network.model.category.Meal
import com.example.mealscatalogue.presenter.OnFinishedListener
import com.example.mealscatalogue.view.MealsCatalogueViewInterface

class MealPresenterImpl(
    private var view: MealsCatalogueViewInterface<List<Meal>>?
): MealPresenterInterface,
    OnFinishedListener<List<Meal>> {

    private val mealNetworkModel = MealNetworkModel(this)

    override fun getMealListByCategory(category: String) {
        mealNetworkModel.downloadData(category)
    }

    override fun success(data: List<Meal>) {
        view?.hideProgressBar()
        view?.showData(data)
    }

    override fun failure(message: String?) {
        view?.hideProgressBar()
        view?.showError(message)
    }

    override fun onDestroyCalled() {
        mealNetworkModel.clearDisposable()
        view = null
    }


}