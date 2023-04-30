package com.hola.appcountries.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hola.appcountries.domain.GetFavoriteMeals
import com.hola.appcountries.domain.InsertIntoFavorites
import com.hola.appcountries.domain.model.MealDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteMeals: GetFavoriteMeals,
    private val insertIntoFavorites: InsertIntoFavorites
): ViewModel(){

    val detailsDataModel = MutableLiveData<List<MealDetailItem>>()

    fun getFavoriteMeals(){
        viewModelScope.launch {
            val details = getFavoriteMeals.onCreate()
            detailsDataModel.value = details
        }
    }

    fun insertFav(id:String){
        viewModelScope.launch {
            insertIntoFavorites.onCreate(id)
        }
    }
}