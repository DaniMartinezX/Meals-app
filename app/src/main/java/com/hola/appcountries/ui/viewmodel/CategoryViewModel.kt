package com.hola.appcountries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.domain.GetCategoriesUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    val categoryModel = MutableLiveData<List<CategoryItemResponse>>()

    private var getCategoriesUseCase = GetCategoriesUseCase()

    fun onCreate(){
        viewModelScope.launch {
            val categories = getCategoriesUseCase()
            categoryModel.value = categories
        }
    }
}

