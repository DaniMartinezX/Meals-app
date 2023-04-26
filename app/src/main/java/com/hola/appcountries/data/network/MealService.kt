package com.hola.appcountries.data.network

import com.hola.appcountries.core.RetrofitHelper
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MealService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCategories(): List<CategoryItemResponse>{
        return withContext(Dispatchers.IO){
            val response: Response<CategoryResponse> = retrofit.create(ApiService::class.java).getCategories("categories.php")

            // Comprobar si la respuesta no es nula y que la lista de categorías dentro de la respuesta no es nula.
            if (response.isSuccessful) {
                val categoryResponse = response.body()
                if (categoryResponse != null && categoryResponse.categories != null) {
                    // Mapear la respuesta a una lista de objetos CategoryItemResponse y devolverla.
                    return@withContext categoryResponse.categories.map {
                        CategoryItemResponse(
                            it.id,
                            it.name,
                            it.image
                        )
                    }
                }
            }

            // En caso contrario, devolver una lista vacía.
            return@withContext emptyList()
        }
    }
}