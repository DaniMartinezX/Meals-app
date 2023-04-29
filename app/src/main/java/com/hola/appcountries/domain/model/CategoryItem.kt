package com.hola.appcountries.domain.model

import com.hola.appcountries.data.database.entities.CategoryEntity
import com.hola.appcountries.data.model.CategoryItemResponse


data class CategoryItem (val name: String, val image: String, val id: String)

//Mappers
fun CategoryItemResponse.toDomain() = CategoryItem(name,image,id)
fun CategoryEntity.toDomain() = CategoryItem(name,image,id)