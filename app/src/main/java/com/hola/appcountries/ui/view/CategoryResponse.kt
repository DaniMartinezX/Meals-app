package com.hola.appcountries.ui.view

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName ("categories") val categories: List<CategoryItemResponse>
)

data class CategoryItemResponse(
    @SerializedName ("idCategory") val id: String,
    @SerializedName ("strCategory") val name: String,
    @SerializedName ("strCategoryThumb") val image: String
)