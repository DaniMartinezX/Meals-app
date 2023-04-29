package com.hola.appcountries.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.domain.GetCategoriesUseCase
import com.hola.appcountries.domain.model.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase:GetCategoriesUseCase
): ViewModel() {

    val categoryModel = MutableLiveData<List<CategoryItem>>()

    fun onCreate(){
        viewModelScope.launch {

            val categories = getCategoriesUseCase()
            Log.i("dani",categories.toString()) //[]
            categoryModel.value = categories
        }
    }
}

