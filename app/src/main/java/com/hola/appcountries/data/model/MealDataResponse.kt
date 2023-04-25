package com.hola.appcountries.data.model

import com.google.gson.annotations.SerializedName

data class MealDataResponse (
    @SerializedName("meals") val meals: List<MealItemResponse>) {
}

data class MealItemResponse (
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val image: String,
    @SerializedName("idMeal") val mealId: String
        )
