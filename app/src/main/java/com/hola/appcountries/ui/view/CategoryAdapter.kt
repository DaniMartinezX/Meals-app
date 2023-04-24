package com.hola.appcountries.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R





class CategoryAdapter(var categoriesList: List<CategoryItemResponse> = emptyList()) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(category: CategoryItemResponse)
    }

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

    override fun onBindViewHolder(viewholder: CategoryViewHolder, position: Int) {
        viewholder.bind(categoriesList[position])
        viewholder.itemView.setOnClickListener{
            listener?.onItemClick(categoriesList[position])
        }
    }
}