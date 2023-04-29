package com.hola.appcountries.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hola.appcountries.domain.model.MealDB

@Entity(tableName = "mealData_table")
data class MealDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "mealId") val mealId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String
)

fun MealDB.toDatabase() = MealDataEntity(mealId = mealId, name = name, image = image)