package com.hola.appcountries.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityFavoritesBinding
import com.hola.appcountries.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var adapterFavorite: FavoriteAdapter
    private lateinit var rvMeals: RecyclerView
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvMeals = binding.rvMeals
        rvMeals.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapterFavorite = FavoriteAdapter (emptyList())
        rvMeals.adapter = adapterFavorite

        favoritesViewModel.getFavoriteMeals()

        favoritesViewModel.detailsDataModel.observe(this){favoriteResponse ->
            favoriteResponse?.let{adapterFavorite.updateList(it)}
        }

        initUI()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailMealActivity::class.java)
        intent.putExtra(DetailMealActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    private fun initUI() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    true
                }
                R.id.home -> {
                    navigateToHome()
                    true
                }
                R.id.profile -> {
                    navigateToProfile()
                    true
                }
                else -> false
            }
        }


    }

    private fun navigateToProfile() {
        val intent = Intent(this,ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}