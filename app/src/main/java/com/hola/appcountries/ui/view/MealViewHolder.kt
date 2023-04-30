package com.hola.appcountries.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ItemMealBinding
import com.hola.appcountries.domain.model.MealDB
import com.squareup.picasso.Picasso

class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMealBinding.bind(view)

    fun bind(mealItemResponse: MealDB, onItemSelected: (String) -> Unit) {
        var isStarFilled = false

        binding.tvMealName.text = mealItemResponse.name
        Picasso.get().load(mealItemResponse.image).into(binding.ivMeal)


        binding.ivFavorites.setOnClickListener {
            if (isStarFilled) {
                binding.ivFavorites.setImageResource(R.drawable.ic_empty_star)
                onItemSelected(mealItemResponse.mealId)
                mealItemResponse.favorite = false
                isStarFilled = false
            } else {
                binding.ivFavorites.setImageResource(R.drawable.ic_full_star)
                onItemSelected(mealItemResponse.mealId)
                mealItemResponse.favorite = true
                isStarFilled = true
            }
        }

        //ROOT ES TODA LA VISTA
        binding.root.setOnClickListener {
            onItemSelected(mealItemResponse.mealId)
        }
    }

}