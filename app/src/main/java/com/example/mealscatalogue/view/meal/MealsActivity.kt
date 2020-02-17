package com.example.mealscatalogue.view.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mealscatalogue.R
import com.example.mealscatalogue.common.CATEGORY_KEY
import com.example.mealscatalogue.common.ITEM_MEAL_IN_ROW
import com.example.mealscatalogue.network.model.category.Meal
import com.example.mealscatalogue.presenter.meal.MealPresenterImpl
import com.example.mealscatalogue.presenter.meal.MealPresenterInterface
import com.example.mealscatalogue.view.meal.recyclerview.MealsAdapter
import com.example.mealscatalogue.view.MealsCatalogueViewInterface
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_meals.*
import kotlinx.android.synthetic.main.error_screen.*

class MealsActivity : AppCompatActivity(), MealsCatalogueViewInterface<List<Meal>> {

    private lateinit var presenter: MealPresenterInterface
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        title = getString(R.string.meal_activity_title)

        presenter = MealPresenterImpl(this)

        category = intent.getStringExtra(CATEGORY_KEY)

        categoryNullability()
    }

    private fun categoryNullability() {
        if (category != null) {
            presenter.getMealListByCategory(category!!)
        } else {
            showError(null)
        }
    }

    override fun hideProgressBar() {
        pb_loading_meals.visibility = View.GONE
    }

    private fun showProgressBar() {
        pb_loading_categories.visibility = View.VISIBLE
    }

    override fun showData(categoriesList: List<Meal>) {
        rv_meals.apply {
            adapter =
                MealsAdapter(
                    categoriesList
                )
            layoutManager = GridLayoutManager(this@MealsActivity, ITEM_MEAL_IN_ROW)
            visibility = View.VISIBLE
        }
    }

    override fun showError(message: String?) {
        setContentView(R.layout.error_screen)

        if (message != null) {
            tv_error_message.text = message
        }

        btn_retry.setOnClickListener {
            setContentView(R.layout.activity_meals)
            showProgressBar()

            categoryNullability()
        }
    }

    override fun onDestroy() {
        presenter.onDestroyCalled()
        super.onDestroy()
    }
}
