package com.hola.appcountries.data.model

import retrofit2.Response

class CategoryProvider {
    companion object{
        lateinit var quotes:Response<CategoryResponse>
    }
}