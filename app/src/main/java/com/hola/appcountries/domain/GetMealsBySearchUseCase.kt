package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.MealItemResponse
import javax.inject.Inject

class GetMealsBySearchUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getMealsBySearch(name:String): List<MealItemResponse>{
        return repository.getMealsBySearch(name)
    }
}