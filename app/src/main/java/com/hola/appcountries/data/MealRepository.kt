package com.hola.appcountries.data

import android.util.Log
import com.hola.appcountries.data.database.dao.CategoryDao
import com.hola.appcountries.data.database.dao.MealDao
import com.hola.appcountries.data.database.dao.MealDetailDao
import com.hola.appcountries.data.database.entities.CategoryEntity
import com.hola.appcountries.data.database.entities.MealDataEntity
import com.hola.appcountries.data.database.entities.MealDetailEntity
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.data.model.CategoryProvider
import com.hola.appcountries.data.model.CategoryResponse
import com.hola.appcountries.data.model.DetailsProvider
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealDataProvider
import com.hola.appcountries.data.model.MealItemResponse
import com.hola.appcountries.data.network.MealService
import com.hola.appcountries.domain.model.CategoryItem
import com.hola.appcountries.domain.model.MealDB
import com.hola.appcountries.domain.model.MealDetailItem
import com.hola.appcountries.domain.model.toDomain
import com.hola.appcountries.domain.model.toDomainCat
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val api: MealService,
    private val mealDetailDao: MealDetailDao,
    private val categoryDao: CategoryDao,
    private val mealDao: MealDao
) {

    suspend fun getFavoriteMealsFromDatabase(): List<MealDetailItem>{
        val response = mealDetailDao.getFavoriteMeals()
        return response.map { it.toDomain() }
    }

    suspend fun getRandomMealFromApi(): List<MealDetailItem>{
        val response = api.getRandomMeal()
        return response.map { it.toDomain() }
    }

    suspend fun getRandomMealFromDatabase(): List<MealDetailItem>{
        val response = mealDetailDao.getRandomMeal()
        return response.map { it.toDomain() }
    }

    suspend fun getDetailsMealFromApi(id: String): List<MealDetailItem> {
        val response = api.getDetailsMeal(id)
        return response.map { it.toDomain() }
    }

    suspend fun getDetailsMealFromDatabase(id: String): List<MealDetailItem> {
        val response = mealDetailDao.getMealDetailsById(id)
        return response.map { it.toDomain() }
    }

    suspend fun insertIntoFavoritesFromDatabase(id:String){
        mealDetailDao.insertInFavorites(id)

    }

    suspend fun getAllCategoriesFromApi(): List<CategoryItem> {
        val response = api.getCategories()
        return response.map { it.toDomain() }
    }

    suspend fun getAllCategoriesFromDatabase(): List<CategoryItem>{
        val response = categoryDao.getCategories()
        return response.map { it.toDomain() }
    }

    suspend fun getMealsByCategoryFromApi(category: String): List<MealDetailItem> {
        val response = api.getMealsByCategory(category)
        return response.map { it.toDomainCat() }
    }

    suspend fun getMealsByCategoryFromDatabase(category: String): List<MealDetailItem> {
        val response = mealDetailDao.getMealsDetailsByCategory(category)
        return response.map { it.toDomain() }
    }

    suspend fun getMealsBySearchFromApi(name: String): List<MealDB> {
        val response: List<MealItemResponse> = api.getMealsBySearch(name)   //Esta línea es innecesaria
        //Mapping
        return response.map { it.toDomain() }
    }

    suspend fun getMealsBySearchFromDatabase(name: String): List<MealDB>{
        val response = mealDao.getMealsByName(name)
        return response.map { it.toDomain()}
    }

    suspend fun insertMeals(meals:List<MealDataEntity>){
        mealDao.insertAll(meals)
    }

    suspend fun clearMealsData() {
        mealDao.deleteAllMealsData()
    }

    suspend fun insertCategories(categories:List<CategoryEntity>) {
        categoryDao.insertAllCategories(categories)
    }

    suspend fun clearCategories() {
        categoryDao.deleteAllCategories()
    }

    suspend fun insertMealsDetails(details:List<MealDetailEntity>) {
        mealDetailDao.insertAllDetails(details)
    }

    suspend fun clearDetails(){
        mealDetailDao.deleteAllMealsDetails()
    }

}