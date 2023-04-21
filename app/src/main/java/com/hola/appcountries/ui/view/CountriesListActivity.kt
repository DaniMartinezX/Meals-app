package com.hola.appcountries.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityCountriesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class CountriesListActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityCountriesListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
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
        adapter = MealAdapter()
        binding.rvMeals.setHasFixedSize(true)
        binding.rvMeals.layoutManager = LinearLayoutManager(this)
        binding.rvMeals.adapter = adapter
        //todo detalle
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<CountryDataResponse> =
                retrofit.create(ApiService::class.java).getMeals("search.php?s=$query")
            if (myResponse.isSuccessful) {
                Log.i("dani","Funciona")
                val response: CountryDataResponse? = myResponse.body()
                if(response != null){
                    runOnUiThread{
                        adapter.updateList(response.meals)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("dani","No funciona")
                showError()
            }

        }

    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
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