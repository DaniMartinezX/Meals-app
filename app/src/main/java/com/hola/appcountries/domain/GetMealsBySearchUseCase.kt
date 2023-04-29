package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.database.entities.toDatabase
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.domain.model.MealDB
import javax.inject.Inject

class GetMealsBySearchUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getMealsBySearch(name:String): List<MealDB>{
        val meals = repository.getMealsBySearchFromApi(name)

        return if (meals.isNotEmpty()){
            repository.clearMealsData()
            repository.insertMeals(meals.map { it.toDatabase() })
            meals
        } else{
            repository.getMealsBySearchFromDatabase(name)
        }
    }
}