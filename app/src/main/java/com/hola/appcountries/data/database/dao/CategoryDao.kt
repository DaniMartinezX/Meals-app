package com.hola.appcountries.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hola.appcountries.data.database.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    suspend fun getCategories():List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(meals:List<CategoryEntity>)

    @Query("DELETE FROM category_table")
    suspend fun deleteAllCategories()
}