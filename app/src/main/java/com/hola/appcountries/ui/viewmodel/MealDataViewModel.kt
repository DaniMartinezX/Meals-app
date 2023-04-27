package com.hola.appcountries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.core.RetrofitHelper
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.data.network.ApiService
import com.hola.appcountries.domain.GetCategoriesUseCase
import com.hola.appcountries.domain.GetMealsByCategory
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MealDataViewModel(category:String): ViewModel() {

    val categoryModel = MutableLiveData<List<CategoryItemResponse>>()
    val mealDataModel = MutableLiveData<List<MealItemResponse>>()

    private var getCategoriesUseCase = GetCategoriesUseCase()
    private var getMealsByCategory = GetMealsByCategory(category)


    fun onCreate(categoryName: String) {
        viewModelScope.launch {
            getCategories()
            getMealsByCategories(categoryName)
        }
    }

    suspend fun getCategories(){
        val categories = getCategoriesUseCase()
        categoryModel.value = categories
    }

    suspend fun getMealsByCategories(category: String){
        getMealsByCategory.invoke(category)
    }
}