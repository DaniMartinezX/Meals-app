package com.hola.appcountries.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hola.appcountries.data.database.entities.MealDataEntity

@Dao
interface MealDao {

    @Query("SELECT * FROM mealData_table WHERE name LIKE :search")
    suspend fun getMealsByName(search:String):List<MealDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals:List<MealDataEntity>)

    @Query("DELETE FROM mealData_table")
    suspend fun deleteAllMealsData()

}