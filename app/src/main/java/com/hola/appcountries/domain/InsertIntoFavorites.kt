package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.domain.model.MealDetailItem
import javax.inject.Inject

class InsertIntoFavorites @Inject constructor(private val repository: MealRepository){

    suspend fun onCreate(id:String){
        repository.insertIntoFavoritesFromDatabase(id)
    }
}