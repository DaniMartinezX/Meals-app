package com.hola.appcountries.ui.view

import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class CountryDataResponse (
    @SerializedName("meals") val meals: List<MealItemResponse>) {
}

data class MealItemResponse (
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val image: String,
    @SerializedName("idMeal") val mealId: String
        )
