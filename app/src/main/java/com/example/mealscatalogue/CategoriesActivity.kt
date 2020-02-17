package com.example.mealscatalogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealscatalogue.common.CATEGORY_KEY
import com.example.mealscatalogue.network.model.categorylist.Category
import com.example.mealscatalogue.presenter.category.CategoryPresenterImpl
import com.example.mealscatalogue.presenter.category.CategoryPresenterInterface
import com.example.mealscatalogue.view.category.CategoriesAdapter
import com.example.mealscatalogue.view.category.ItemClickListener
import com.example.mealscatalogue.view.MealsCatalogueViewInterface
import com.example.mealscatalogue.view.meal.MealsActivity
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.error_screen.*

class CategoriesActivity : AppCompatActivity(), MealsCatalogueViewInterface<List<Category>> {

    private lateinit var presenter: CategoryPresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        title = getString(R.string.categories_activity_title)

        presenter = CategoryPresenterImpl(this)

        presenter.getCategoryList()
    }

    override fun hideProgressBar() {
        pb_loading_categories.visibility = View.GONE
    }

    private fun showProgressBar() {
        pb_loading_categories.visibility = View.VISIBLE
    }

    override fun showData(categoriesList: List<Category>) {
        rv_categories.apply {
            adapter = CategoriesAdapter(
                categoriesList,
                setOnClickListener()
            )
            layoutManager = LinearLayoutManager(this@CategoriesActivity)
            visibility = View.VISIBLE
        }
    }

    override fun showError(message: String?) {
        setContentView(R.layout.error_screen)

        if (message != null) {
            tv_error_message.text = message
        }

        btn_retry.setOnClickListener {
            setContentView(R.layout.activity_categories)
            showProgressBar()
            presenter.getCategoryList()
        }
    }

    private fun setOnClickListener(): ItemClickListener {
        return object :
            ItemClickListener {

            override fun onItemClicked(category: String) {
                val intent = Intent(this@CategoriesActivity, MealsActivity::class.java).apply {
                    putExtra(CATEGORY_KEY, category)
                }

                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroyCalled()
        super.onDestroy()
    }
}
