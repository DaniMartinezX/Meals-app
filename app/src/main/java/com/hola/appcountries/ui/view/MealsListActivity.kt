package com.hola.appcountries.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.data.model.MealDataResponse
import com.hola.appcountries.data.network.ApiService
import com.hola.appcountries.data.model.CategoryItemResponse
import com.hola.appcountries.databinding.ActivityMealsListBinding
import com.hola.appcountries.ui.view.DetailMealActivity.Companion.EXTRA_ID
import com.hola.appcountries.ui.viewmodel.CategoryViewModel
import com.hola.appcountries.ui.viewmodel.MealDataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsListActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityMealsListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MealAdapter
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvMeals: RecyclerView
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var mealDataViewModel: MealDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()

        //Cargando categorías
        rvCategories = binding.rvCategories
        rvCategories.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        adapterCategory = CategoryAdapter(emptyList())
        rvCategories.adapter = adapterCategory

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.categoryModel.observe(this) { categoryResponse ->
            categoryResponse?.let { adapterCategory.updateList(it) }
        }
        categoryViewModel.onCreate()

        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })
        adapterCategory = CategoryAdapter()
        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = adapterCategory




        //Filtrado por categorías al seleccionar una
        adapterCategory.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: CategoryItemResponse) {
                val categoryName = category.name

                // Aquí puedes hacer algo con el nombre de la categoría seleccionada
                CoroutineScope(Dispatchers.IO).launch {
                    val myResponse: Response<MealDataResponse> =
                        retrofit.create(ApiService::class.java).getMealsByCategory("filter.php?c=$categoryName")
                    if (myResponse.isSuccessful) {
                        Log.i("dani", "Funciona filtrado")
                        val response: MealDataResponse? = myResponse.body()
                        if (response != null) {
                            runOnUiThread {
                                adapter.updateList(response.meals)
                                binding.progressBar.isVisible = false
                            }
                        }
                    } else {
                        Log.i("dani", "No funciona")
                        showError()
                    }
                }
            }
        })


        // "it" para indicar el elemento que le hace falta a la función landa del correspondiente item
        adapter = MealAdapter { navigateToDetail(it) }
        binding.rvMeals.setHasFixedSize(true)
        binding.rvMeals.layoutManager = LinearLayoutManager(this)
        binding.rvMeals.adapter = adapter


    }



    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<MealDataResponse> =
                retrofit.create(ApiService::class.java).getMeals("search.php?s=$query")
            if (myResponse.isSuccessful) {
                Log.i("dani", "Funciona")
                val response: MealDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.meals)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("dani", "No funciona")
                showError()
            }

        }

    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailMealActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}