package com.hola.appcountries.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.databinding.ActivityMealsListBinding
import com.hola.appcountries.domain.model.CategoryItem
import com.hola.appcountries.domain.model.toDomain
import com.hola.appcountries.ui.view.DetailMealActivity.Companion.EXTRA_ID
import com.hola.appcountries.ui.viewmodel.CategoryViewModel
import com.hola.appcountries.ui.viewmodel.MealDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MealsListActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityMealsListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MealAdapter
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var rvCategories: RecyclerView
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val mealDataViewModel: MealDataViewModel by viewModels()

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

        categoryViewModel.onCreate()

        categoryViewModel.categoryModel.observe(this) { categoryResponse ->
            categoryResponse?.let { adapterCategory.updateList(it) }
        }


        initUI()
    }


    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })

        //Filtrado por categorías al seleccionar una
        adapterCategory.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: CategoryItem) {
                val categoryName = category.name    //Da el nombre
                mealDataViewModel.onCreate(categoryName)
                runOnUiThread {
                    mealDataViewModel.detailsDataModel.observe(this@MealsListActivity) { mealsResponse ->
                        val meals = mealsResponse.map { it.toDomain() }
                        mealsResponse?.let { adapter.updateList(meals)}
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

        mealDataViewModel.searchByName(query)
        runOnUiThread {
            mealDataViewModel.mealDataModel.observe(this@MealsListActivity){ mealsResponse ->
                mealsResponse?.let { adapter.updateList(it) }
            }
        }
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