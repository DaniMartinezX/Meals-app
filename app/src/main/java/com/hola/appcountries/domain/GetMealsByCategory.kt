package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.MealItemResponse

class GetMealsByCategory(var category: String) {

    private val repository = MealRepository()

    suspend operator fun invoke(category: String):List<MealItemResponse> = repository.getMealsByCategory(category)
}