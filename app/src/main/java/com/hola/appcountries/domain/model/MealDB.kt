package com.hola.appcountries.domain.model

import com.hola.appcountries.data.database.entities.MealDataEntity
import com.hola.appcountries.data.model.MealItemResponse

data class MealDB (val name: String,val image: String,val mealId: String)

//Mappers
fun MealItemResponse.toDomain() = MealDB(name,image,mealId)
fun MealDataEntity.toDomain() = MealDB(name,image,mealId)
fun MealDetailItem.toDomain() = MealDB(name,image, id)