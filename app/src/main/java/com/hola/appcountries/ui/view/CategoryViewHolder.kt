package com.hola.appcountries.ui.view

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.databinding.ItemCategoryBinding
import com.hola.appcountries.domain.model.CategoryItem
import com.squareup.picasso.Picasso

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoryBinding.bind(view)
    val flSelection: FrameLayout = binding.flSelection


    fun bind(categoryItemResponse: CategoryItem){
        binding.tvCategory.text = categoryItemResponse.name
        Picasso.get().load(categoryItemResponse.image).into(binding.ivCategory)
    }
}