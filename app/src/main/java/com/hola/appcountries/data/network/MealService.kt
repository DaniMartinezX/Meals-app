package com.hola.appcountries.data.network

import com.hola.appcountries.core.RetrofitHelper
import com.hola.appcountries.data.model.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCategories(): CategoryResponse{
        return withContext(Dispatchers.IO){
            val response:Response<CategoryResponse> = retrofit.create(ApiService::class.java).getCategories("categories.php")
            response.body()!!
        }
    }
}