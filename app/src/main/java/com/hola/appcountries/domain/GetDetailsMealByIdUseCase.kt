package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.database.entities.toDatabase
import com.hola.appcountries.domain.model.MealDetailItem
import javax.inject.Inject

class GetDetailsMealByIdUseCase @Inject constructor(private val repository: MealRepository){

    suspend fun getDetailsMeal(id:String): List<MealDetailItem>{
        val details = repository.getDetailsMealFromApi(id)

        return if (details.isNotEmpty()){
            repository.clearDetails()
            repository.insertMealsDetails(details.map { it.toDatabase() })
            details
        } else {
            repository.getDetailsMealFromDatabase(id)
        }
    }
}