package com.example.mealscatalogue.view.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealscatalogue.R
import com.example.mealscatalogue.network.model.categorylist.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(
    private val categoryList: List<Category>,
    private val onItemClickListener: ItemClickListener
): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoryList[position]

        holder.bindItem(currentCategory)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(
        private val viewItem: View
    ): RecyclerView.ViewHolder(viewItem){

        fun bindItem(currentCategory: Category) {
            val name = currentCategory.strCategory
            val description = currentCategory.strCategoryDescription
            val imgUrl = currentCategory.strCategoryThumb

            viewItem.apply {
                tv_category_name.text = name
                tv_category_description.text = description

                Picasso
                    .get()
                    .load(imgUrl)
                    .into(img_category)

                setOnClickListener {
                    onItemClickListener.onItemClicked(name)
                }
            }
        }
    }
}