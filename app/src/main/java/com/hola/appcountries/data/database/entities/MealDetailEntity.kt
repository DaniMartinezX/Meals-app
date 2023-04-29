package com.hola.appcountries.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hola.appcountries.domain.model.MealDetailItem

@Entity(tableName = "mealDetail_table")
data class MealDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "area") val area: String,
    @ColumnInfo(name = "instructions") val instructions: String
)

fun MealDetailItem.toDatabase() = MealDetailEntity(id = id, name = name, image = image, category = category, area = area, instructions = instructions)