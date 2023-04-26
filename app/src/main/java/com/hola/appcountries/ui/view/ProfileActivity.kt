package com.hola.appcountries.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityProfileBinding

enum class ProviderType{
    BASIC
}

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        initUI()
    }

    private fun setup(email: String, provider: String) {
        binding.tvEmail.text = email
        binding.tvProvider.text = provider
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            navigateToAuth()
        }
    }

    private fun navigateToAuth(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun initUI() {

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    navigateToFavorites()
                    true
                }
                R.id.home -> {
                    navigateToHome()
                    true
                }
                R.id.profile -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFavorites() {
        val intent = Intent(this, FavoritesActivity::class.java)
        startActivity(intent)
    }
}