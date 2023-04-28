package com.hola.appcountries.data

import android.util.Log
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.DetailsProvider
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealDataProvider
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.data.network.MealService
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val api: MealService,
    private val categoryProvider: CategoryProvider,
    private val mealDataProvider: MealDataProvider,
    private val detailsProvider: DetailsProvider
) {

    suspend fun getRandomMeal(): List<Meal>{
        val response = api.getRandomMeal()
        detailsProvider.details = response
        return response
    }

    suspend fun getDetailsMeal(id: String): List<Meal> {
        val response = api.getDetailsMeal(id)
        detailsProvider.details = response
        return response
    }

    suspend fun getAllCategories(): List<CategoryItemResponse> {
        val response = api.getCategories()
        Log.i("dani", "Traballa o repositorio")
        categoryProvider.categories = response
        return response
    }

    suspend fun getMealsByCategory(category: String): List<MealItemResponse> {
        val response = api.getMealsByCategory(category)
        mealDataProvider.meals = response
        return response
    }

    suspend fun getMealsBySearch(name: String): List<MealItemResponse> {
        val response = api.getMealsBySearch(name)
        mealDataProvider.meals = response
        return response
    }

}