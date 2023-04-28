package com.hola.appcountries.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.domain.GetDetailsMealByIdUseCase
import com.hola.appcountries.domain.GetMealsByCategory
import com.hola.appcountries.domain.GetMealsBySearchUseCase
import com.hola.appcountries.domain.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MealDataViewModel @Inject constructor(
    private val getMealsByCategory: GetMealsByCategory,
    private val getMealsBySearchUseCase: GetMealsBySearchUseCase,
    private val getDetailsMealByIdUseCase: GetDetailsMealByIdUseCase,
    private val getRandomMealUseCase: GetRandomMealUseCase
): ViewModel() {

    var mealDataModel = MutableLiveData<List<MealItemResponse>>()
    var detailsDataModel = MutableLiveData<List<Meal>>()

    fun onCreate(category:String){
        viewModelScope.launch {
            val meals = getMealsByCategory.getMealsByCategory(category)
            mealDataModel.value = meals
        }
    }

    fun searchByName(name:String){
        viewModelScope.launch {
            val meals = getMealsBySearchUseCase.getMealsBySearch(name)
            mealDataModel.value = meals
        }
    }

    fun getDetailsMeal(id:String){
        viewModelScope.launch {
            val details = getDetailsMealByIdUseCase.getDetailsMeal(id)
            detailsDataModel.value = details
        }
    }

    fun getRandomMeal(){
        viewModelScope.launch {
            val details = getRandomMealUseCase.getRandomMeal()
            detailsDataModel.value = details
        }
    }

    }
