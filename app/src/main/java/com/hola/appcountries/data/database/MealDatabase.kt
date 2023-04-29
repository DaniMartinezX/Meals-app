package com.hola.appcountries.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hola.appcountries.data.database.dao.CategoryDao
import com.hola.appcountries.data.database.dao.MealDao
import com.hola.appcountries.data.database.dao.MealDetailDao
import com.hola.appcountries.data.database.entities.CategoryEntity
import com.hola.appcountries.data.database.entities.MealDataEntity
import com.hola.appcountries.data.database.entities.MealDetailEntity

@Database(entities = [MealDataEntity::class, MealDetailEntity::class, CategoryEntity::class], version = 1)
abstract class MealDatabase: RoomDatabase() {

    abstract fun getMealDataDao():MealDao
    abstract fun getMealDetailDao():MealDetailDao
    abstract fun getCategoryDao():CategoryDao
}