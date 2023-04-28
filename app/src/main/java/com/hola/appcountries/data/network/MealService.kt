package com.hola.appcountries.data.network

import android.util.Log
import androidx.core.view.isVisible
import com.hola.appcountries.core.RetrofitHelper
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealDataResponse
import com.hola.appcountries.data.model.MealDetailResponse
import com.hola.appcountries.data.model.MealItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MealService @Inject constructor(private val api:ApiService){

    suspend fun getRandomMeal(): List<Meal>{
        val response = api.getRandomMeal()
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList<Meal>()
        }
    }

    suspend fun getCategories(): List<CategoryItemResponse>{
        val response =  api.getCategories()
        Log.i("dani", response.toString())
        return if (response.isSuccessful && response.body()!= null){
            response.body()!!.categories
        } else {
            emptyList<CategoryItemResponse>()
        }
    }

    suspend fun getDetailsMeal(id:String):List<Meal>{
        val response = api.getDetailsMealId(id)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList<Meal>()
        }
    }

    suspend fun getMealsBySearch(name:String):List<MealItemResponse>{
        val response = api.getMeals(name)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList<MealItemResponse>()
        }
    }

    suspend fun getMealsByCategory(categoryName:String):List<MealItemResponse>{
        val response = api.getMealsByCategory(categoryName)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList<MealItemResponse>()
        }
    }


}