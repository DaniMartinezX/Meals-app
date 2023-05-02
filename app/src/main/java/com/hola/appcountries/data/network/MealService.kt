package com.hola.appcountries.data.network

import android.util.Log
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealItemResponse
import javax.inject.Inject

class MealService @Inject constructor(private val api:ApiService){

    suspend fun getRandomMeal(): List<Meal>{
        val response = api.getRandomMeal()
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList()
        }
    }

    suspend fun getCategories(): List<CategoryItemResponse>{
        val response =  api.getCategories()
        Log.i("dani", response.toString())
        return if (response.isSuccessful && response.body()!= null){
            response.body()!!.categories
        } else {
            emptyList()
        }
    }

    suspend fun getDetailsMeal(id:String):List<Meal>{
        val response = api.getDetailsMealId(id)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList()
        }
    }

    suspend fun getMealsBySearch(name:String):List<MealItemResponse>{
        val response = api.getMeals(name)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList()
        }
    }

    suspend fun getMealsByCategory(categoryName:String):List<MealItemResponse>{
        val response = api.getMealsByCategory(categoryName)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList()
        }
    }


}