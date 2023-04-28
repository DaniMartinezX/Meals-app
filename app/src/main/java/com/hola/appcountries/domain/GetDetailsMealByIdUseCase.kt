package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.Meal
import javax.inject.Inject

class GetDetailsMealByIdUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getDetailsMeal(id:String): List<Meal>{
        return repository.getDetailsMeal(id)
    }
}