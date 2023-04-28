package com.hola.appcountries.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryProvider @Inject constructor() {
    var categories: List<CategoryItemResponse> = emptyList()
}