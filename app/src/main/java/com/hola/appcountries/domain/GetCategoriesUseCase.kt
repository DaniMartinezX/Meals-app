package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.CategoryResponse
import retrofit2.Response

class GetCategoriesUseCase {

    private val repository = MealRepository()

    /*
    suspend operator fun invoke():Response<CategoryResponse>?{
        return repository
    }

     */
}