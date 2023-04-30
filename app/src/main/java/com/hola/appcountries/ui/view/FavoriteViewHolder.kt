package com.hola.appcountries.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ItemFavoriteBinding
import com.hola.appcountries.domain.model.MealDetailItem
import com.squareup.picasso.Picasso

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding: ItemFavoriteBinding
        get() = ItemFavoriteBinding.bind(itemView)

    fun bind(mealItemResponse: MealDetailItem){

        var isStarFilled = true

        binding.tvMealName.text = mealItemResponse.name
        Picasso.get().load(mealItemResponse.image).into(binding.ivMeal)

        binding.ivFavorites.setOnClickListener {
            if (isStarFilled) {
                binding.ivFavorites.setImageResource(R.drawable.ic_empty_star)
                //todo sacar meal de favoritos
                isStarFilled = false
            }
        }

    }

}