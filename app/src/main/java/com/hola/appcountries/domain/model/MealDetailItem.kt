package com.hola.appcountries.domain.model

import com.hola.appcountries.data.database.entities.MealDetailEntity
import com.hola.appcountries.data.model.Meal
import com.hola.appcountries.data.model.MealItemResponse

data class MealDetailItem(
    val id: String,
    val name: String,
    val image: String,
    val category: String,
    val area: String,
    val instructions: String
)

//Mappers
fun MealDetailEntity.toDomain() = MealDetailItem(id,name,image,category,area,instructions)
fun MealItemResponse.toDomainCat() = MealDetailItem(id = mealId, name = name, image = image, area = "", category = "", instructions = "")
fun Meal.toDomain() = MealDetailItem(id,name,image,category,area,instructions)