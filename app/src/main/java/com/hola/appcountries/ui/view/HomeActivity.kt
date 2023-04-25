package com.hola.appcountries.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityHomeBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.flMeal.isVisible = false
        binding.fabSearch.setOnClickListener {
            navigateToSearch()
        }
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    navigateToFavorites()
                    true
                }
                R.id.placeholder -> {
                    // Aquí puedes escribir el código que se ejecutará cuando se seleccione el botón "Placeholder"
                    true
                }
                R.id.profile -> {
                    navigateToProfile()
                    true
                }
                else -> false
            }
        }



        binding.btnImage.setOnClickListener {
            binding.flMeal.isVisible = true
            CoroutineScope(Dispatchers.IO).launch {
                val myResponse: Response<MealDetailResponse> =
                retrofit.create(ApiService::class.java).getRandomMeal("random.php")
                if(myResponse.isSuccessful){
                    val response: MealDetailResponse? = myResponse.body()
                    if (response != null){
                        runOnUiThread{ dataReceived(response)}
                    }
                }
            }
        }


    }


    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailMealActivity::class.java)
        intent.putExtra(DetailMealActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    private fun dataReceived(meal: MealDetailResponse) {
        val mealObj = meal.meals[0] //Tiene sólo un objeto el Array !!!!
        val imageUrl = mealObj.image
        Picasso.get().load(imageUrl).into(binding.ivMeal)
        val nameMeal = mealObj.name
        binding.tvMealName.text = nameMeal
        val nameCategory = mealObj.category
        binding.tvCategoryName.text = nameCategory
        val idMeal = mealObj.id
        binding.cvMeal.setOnClickListener { navigateToDetail(idMeal) }
    }


    private fun navigateToProfile() {
        val intent = Intent(this,ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFavorites() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearch() {
        val intent = Intent(this, MealsListActivity::class.java)
        startActivity(intent)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}