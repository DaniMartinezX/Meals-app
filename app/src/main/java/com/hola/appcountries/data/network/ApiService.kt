package com.hola.appcountries.data.network

import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.MealDataResponse
import com.hola.appcountries.data.model.MealDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getMeals(@Url url:String): Response<MealDataResponse>

    @GET
    suspend fun getDetailsMealId(@Url url:String): Response<MealDetailResponse>

    @GET
    suspend fun getCategories(@Url url:String): Response<CategoryResponse>

    @GET
    suspend fun getMealsByCategory(@Url url:String): Response<MealDataResponse>

    @GET
    suspend fun getRandomMeal(@Url url:String): Response<MealDetailResponse>
}