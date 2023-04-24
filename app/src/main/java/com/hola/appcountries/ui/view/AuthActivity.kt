package com.hola.appcountries.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.credentials.*
import com.google.firebase.auth.FirebaseAuth
import com.hola.appcountries.HomeActivity
import com.hola.appcountries.HomeActivity.Companion.EMAIL
import com.hola.appcountries.HomeActivity.Companion.PROVIDER
import com.hola.appcountries.ProviderType
import com.hola.appcountries.R
import com.hola.appcountries.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var credentialsClient: CredentialsClient
    private lateinit var hintRequest: HintRequest




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        credentialsClient = Credentials.getClient(this)
        hintRequest = HintRequest.Builder()
            .setHintPickerConfig(
                CredentialPickerConfig.Builder()
                    .setShowCancelButton(true)
                    .build()
            )
            .setEmailAddressIdentifierSupported(true)
            .build()

        initUI()
        session()
    }

    override fun onStart() {
        super.onStart()

        binding.lyCredentials.visibility = View.VISIBLE
    }

    private fun session() {
        //Sin .edit porque estamos recuperando datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString(EMAIL, null)
        val provider = prefs.getString(PROVIDER, null)

        //Sesión iniciada
        if (email != null && provider != null) {
            binding.lyCredentials.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }


    }

    private fun initUI() {
        title = "Autentificación"

        //Cuando el usuario hace click en "Registrarse"
        binding.singUpButton.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        //Cuando el usuario hace click en "Acceder"
        binding.loginButton.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.googleButton.setOnClickListener {

        }
    }

    //Función que muestra error al no haber podido autenticar al usuario.
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al autenticar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(EMAIL, email)
            putExtra(PROVIDER, provider.name)
        }
        startActivity(intent)
    }

}