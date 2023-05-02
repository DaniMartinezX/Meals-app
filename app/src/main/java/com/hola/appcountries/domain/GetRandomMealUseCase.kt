package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.database.entities.toDatabase
import com.hola.appcountries.domain.model.MealDetailItem
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getRandomMeal():List<MealDetailItem>{
        val details = repository.getRandomMealFromApi()

        return if (details.isNotEmpty()){
            repository.clearDetails()
            repository.insertMealsDetails(details.map { it.toDatabase() })
            details
        } else {
            repository.getRandomMealFromDatabase()
        }
    }
}