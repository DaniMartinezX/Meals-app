package com.hola.appcountries

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.hola.appcountries.databinding.ActivityAuthBinding
import com.hola.appcountries.databinding.ActivityHomeBinding


enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object {
        const val EMAIL = "email"
        const val PROVIDER = "provider"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString(EMAIL)
        val provider = bundle?.getString(PROVIDER)
        initUI(email ?: "", provider ?: "")

        // Guardado de datos    Acceso private a este fichero
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString(EMAIL, email)
        prefs.putString(PROVIDER, provider)
        //Guardamos datos
        prefs.apply()
    }

    private fun initUI(email:String, provider:String) {

        title = "Inicio"

        binding.tvEmail.text = email
        binding.tvProvider.text = provider

        binding.logOutButton.setOnClickListener{

            //Borrado de datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            finish()
        }

    }
}