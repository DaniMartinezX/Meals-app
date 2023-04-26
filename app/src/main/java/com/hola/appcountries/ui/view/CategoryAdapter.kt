package com.hola.appcountries.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryResponse

class CategoryAdapter(var categoriesList: List<CategoryItemResponse> = emptyList()) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(category: CategoryItemResponse)
    }

    private var selectedItemIndex = -1
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun updateList(list: List<CategoryItemResponse>) {
        categoriesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(viewholder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        viewholder.bind(categoriesList[position])
        if (selectedItemIndex == position) {
            viewholder.flSelection.setBackgroundColor(ContextCompat.getColor(viewholder.itemView.context, R.color.meal_item_name_selected))
        } else {
            viewholder.flSelection.setBackgroundColor(ContextCompat.getColor(viewholder.itemView.context, R.color.meal_item_name_unselected))
        }
        viewholder.itemView.setOnClickListener{
            val prevSelectedItemIndex = selectedItemIndex
            selectedItemIndex = position
            if (prevSelectedItemIndex != -1) {
                notifyItemChanged(prevSelectedItemIndex)
            }
            notifyItemChanged(selectedItemIndex)
            listener?.onItemClick(categoriesList[position])
        }
    }

}