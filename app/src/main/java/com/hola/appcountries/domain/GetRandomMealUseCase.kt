package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.Meal
import javax.inject.Inject

class GetRandomMealUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getRandomMeal():List<Meal>{
        return repository.getRandomMeal()
    }
}