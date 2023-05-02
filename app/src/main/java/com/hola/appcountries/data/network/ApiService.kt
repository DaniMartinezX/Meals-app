package com.hola.appcountries.data.network

import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.MealDataResponse
import com.hola.appcountries.data.model.MealDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun getMeals(@Query("s") mealName: String): Response<MealDataResponse>

    @GET("lookup.php")
    suspend fun getDetailsMealId(@Query("i") id: String): Response<MealDetailResponse>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName:String): Response<MealDataResponse>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealDetailResponse>
}