package com.hola.appcountries.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R

class MealAdapter(var mealList:List<MealItemResponse> = emptyList()): RecyclerView.Adapter<MealViewHolder>() {

    fun updateList(list : List<MealItemResponse>){
        mealList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        )
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(viewholder: MealViewHolder, position: Int) {
        viewholder.bind(mealList[position])
    }
}