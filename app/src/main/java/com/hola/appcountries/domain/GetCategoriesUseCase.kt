package com.hola.appcountries.domain

import com.hola.appcountries.data.MealRepository
import com.hola.appcountries.data.database.entities.toDatabase
import com.hola.appcountries.domain.model.CategoryItem
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {


    suspend operator fun invoke(): List<CategoryItem> {
        val categories = repository.getAllCategoriesFromApi()

        return if(categories.isNotEmpty()){
            repository.clearCategories()
            repository.insertCategories(categories.map { it.toDatabase() })
            categories
        } else {
            repository.getAllCategoriesFromDatabase()
        }
    }


}