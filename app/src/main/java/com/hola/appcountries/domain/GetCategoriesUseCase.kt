package com.hola.appcountries.domain

import android.util.Log
import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryProvider: CategoryProvider,
    private val repository: MealRepository
) {


    operator fun invoke(): List<CategoryItemResponse> = runBlocking {
        val categories = withContext(Dispatchers.IO) { repository.getAllCategories() }
        Log.i("dani", categories.toString())
        if (categories.isNotEmpty()) {
            categories
        } else {
            emptyList()
        }
    }


}