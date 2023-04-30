package com.hola.appcountries.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hola.appcountries.data.database.entities.MealDetailEntity

@Dao
interface MealDetailDao {

    @Query("SELECT * FROM mealDetail_table WHERE category LIKE :search")
    suspend fun getMealsDetailsByCategory(search:String):List<MealDetailEntity>

    @Query("SELECT * FROM mealDetail_table WHERE id = :search")
    suspend fun getMealDetailsById(search:String): List<MealDetailEntity>

    @Query("SELECT * FROM mealDetail_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomMeal():List<MealDetailEntity>

    @Query("SELECT * FROM mealDetail_table WHERE favorite = 1")
    suspend fun getFavoriteMeals():List<MealDetailEntity>

    @Query("UPDATE mealDetail_table SET favorite=1 WHERE id=:id")
    suspend fun insertInFavorites(id:String): Int

    @Query("UPDATE mealDetail_table SET favorite=1 WHERE id=:id")
    suspend fun discardFromFavorites(id:String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(meals:List<MealDetailEntity>)

    @Query("DELETE FROM mealDetail_table")
    suspend fun deleteAllMealsDetails()
}