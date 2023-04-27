package com.hola.appcountries.data.network

import android.util.Log
import androidx.core.view.isVisible
import com.hola.appcountries.core.RetrofitHelper
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.MealDataResponse
import com.hola.appcountries.data.model.MealItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealService {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val categoryModel = CategoryProvider

    suspend fun getMealsByCategory(categoryName:String):List<MealItemResponse>{
        val response = retrofit.create(ApiService::class.java).getMealsByCategory(categoryName)
        return if (response.isSuccessful && response.body() != null){
            response.body()!!.meals
        } else {
            emptyList<MealItemResponse>()
        }
    }

    suspend fun getCategories(): List<CategoryItemResponse>{
        val response =  retrofit.create(ApiService::class.java).getCategories()
        return if (response.isSuccessful && response.body()!= null){
            response.body()!!.categories
        } else {
            emptyList<CategoryItemResponse>()
        }
    }
}