package com.hola.appcountries.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.signUpButton.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showAccount(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            } else {
                Toast.makeText(this, "An error has occurred, you must fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginButton.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showAccount(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            } else {
                Toast.makeText(this, "An error has occurred, you must fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("An error has occurred authenticating the user")
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAccount(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, ProfileActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}