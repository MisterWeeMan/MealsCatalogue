package com.example.mealscatalogue.view.meal.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealscatalogue.R
import com.example.mealscatalogue.network.model.category.Meal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_meal.view.*

class MealsAdapter(
    private val mealList: List<Meal>
): RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentMeal = mealList[position]

        holder.bindItem(currentMeal)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class MealViewHolder(
        private val viewItem: View
    ): RecyclerView.ViewHolder(viewItem) {

        fun bindItem(currentMeal: Meal) {
            val name = currentMeal.strMeal
            val imgUrl = currentMeal.strMealThumb

            viewItem.apply {
                tv_meal_name.text = name

                Picasso
                    .get()
                    .load(imgUrl)
                    .into(img_meal)
            }
        }

    }
}