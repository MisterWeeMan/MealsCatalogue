package com.example.mealscatalogue.presenter.category

import com.example.mealscatalogue.model.CategoryNetworkModel
import com.example.mealscatalogue.network.model.categorylist.Category
import com.example.mealscatalogue.presenter.OnFinishedListener
import com.example.mealscatalogue.view.MealsCatalogueViewInterface

class CategoryPresenterImpl(
    private var view: MealsCatalogueViewInterface<List<Category>>?
): CategoryPresenterInterface,
    OnFinishedListener<List<Category>> {

    private val networkModel = CategoryNetworkModel(this)

    override fun getCategoryList() {
        networkModel.downloadData()
    }

    override fun success(data: List<Category>) {
        view?.hideProgressBar()
        view?.showData(data)
    }

    override fun failure(message: String?) {
        view?.hideProgressBar()
        view?.showError(message)
    }

    override fun onDestroyCalled() {
        networkModel.clearDisposable()
        view = null
    }


}