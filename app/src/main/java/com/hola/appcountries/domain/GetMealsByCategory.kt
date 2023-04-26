package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.MealItemResponse

class GetMealsByCategory(private val category: String) {

    private val repository = MealRepository()

    suspend operator fun invoke():List<MealItemResponse> = repository.getMealsByCategory(category)
}