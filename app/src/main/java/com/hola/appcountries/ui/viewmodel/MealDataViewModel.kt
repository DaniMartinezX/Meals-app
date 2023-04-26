package com.hola.appcountries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.domain.GetMealsByCategory
import kotlinx.coroutines.launch

class MealDataViewModel(category:String): ViewModel() {

    val mealDataModel = MutableLiveData<List<MealItemResponse>>()

    private var getMealsByCategory = GetMealsByCategory(category)

    fun onCreate(categoryName: String) {
        viewModelScope.launch {
            val meals = getMealsByCategory()
            mealDataModel.value = meals
        }
    }
}