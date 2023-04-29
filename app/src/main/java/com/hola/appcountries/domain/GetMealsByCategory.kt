package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.database.entities.toDatabase
import com.hola.appcountries.domain.model.MealDetailItem
import javax.inject.Inject


class GetMealsByCategory @Inject constructor(private val repository: MealRepository) {


    suspend fun getMealsByCategory(category: String): List<MealDetailItem> {
        val meals = repository.getMealsByCategoryFromApi(category)
        return if (meals.isNotEmpty()){
            repository.clearDetails()
            repository.insertMealsDetails(meals.map { it.toDatabase() })
            meals
        } else {
            repository.getMealsByCategoryFromDatabase(category)
        }

    }
}