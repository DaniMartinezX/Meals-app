package com.hola.appcountries.data.model

import com.google.gson.annotations.SerializedName

data class MealDetailResponse(
    @SerializedName("meals") val meals: List<Meal>
) {}

data class Meal(
    @SerializedName("strMeal") val name: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strArea") val area: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strMealThumb") val image: String,
    @SerializedName("idMeal") val id: String
)

