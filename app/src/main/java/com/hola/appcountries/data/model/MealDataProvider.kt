package com.hola.appcountries.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealDataProvider @Inject constructor() {
    var meals: List<MealItemResponse> = emptyList()
}