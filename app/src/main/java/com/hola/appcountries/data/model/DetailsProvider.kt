package com.hola.appcountries.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsProvider @Inject constructor() {
    var details: List<Meal> = emptyList()
}