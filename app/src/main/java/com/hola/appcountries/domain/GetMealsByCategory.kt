package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.MealItemResponse
import javax.inject.Inject


class GetMealsByCategory @Inject constructor(private val repository: MealRepository) {


    suspend fun getMealsByCategory(category: String): List<MealItemResponse> {
        return repository.getMealsByCategory(category)
    }
}