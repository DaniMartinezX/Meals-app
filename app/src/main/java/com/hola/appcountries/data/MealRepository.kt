package com.hola.appcountries.data

import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.network.MealService

class MealRepository {

    private val api = MealService()


    suspend fun getAllCategories():List<CategoryItemResponse>{
        return api.getCategories()
    }

}