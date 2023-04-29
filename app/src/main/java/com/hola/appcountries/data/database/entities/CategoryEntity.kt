package com.hola.appcountries.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hola.appcountries.domain.model.CategoryItem

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String
)

fun CategoryItem.toDatabase() = CategoryEntity(id = id, name = name, image = image)