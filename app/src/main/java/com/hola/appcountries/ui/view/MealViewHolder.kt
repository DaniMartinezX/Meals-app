package com.hola.appcountries.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.databinding.ItemMealBinding
import com.squareup.picasso.Picasso

class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMealBinding.bind(view)

    fun bind(mealItemResponse: MealItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvMealName.text = mealItemResponse.name
        Picasso.get().load(mealItemResponse.image).into(binding.ivMeal)

        //ROOT ES TODA LA VISTA
        binding.root.setOnClickListener {
            onItemSelected(mealItemResponse.mealId)
        }
    }
}