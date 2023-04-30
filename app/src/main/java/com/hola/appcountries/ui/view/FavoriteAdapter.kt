package com.hola.appcountries.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R
import com.hola.appcountries.domain.model.MealDetailItem

class FavoriteAdapter(
    var mealList: List<MealDetailItem> = emptyList()
) :
    RecyclerView.Adapter<FavoriteViewHolder>()  {
    fun updateList(list: List<MealDetailItem>) {
        mealList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int = mealList.size
}