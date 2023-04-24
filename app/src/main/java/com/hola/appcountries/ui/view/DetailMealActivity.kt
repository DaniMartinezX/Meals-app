package com.hola.appcountries.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray;
import com.google.gson.Gson
import com.hola.appcountries.databinding.ActivityDetailMealBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailMealActivity : AppCompatActivity() {

    //Constante para el extra del activity
    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recuperación de la ID
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()  //Al poder dar null el ID que mande en ese caso un vacío para que no haya error
        getMealInformation(id)
    }

    private fun getMealInformation(id: String) {
        Log.i("dani","Antes da corrutina")
        CoroutineScope(Dispatchers.IO).launch {
            val mealDetail = getRetrofit().create(ApiService::class.java).getDetailsMealId("lookup.php?i=$id")

            Log.i("dani","Inicio de datos")
            //Se mira si hay datos dentro del response
            if(mealDetail.body() != null){
                Log.i("dani",mealDetail.body().toString())
                runOnUiThread{ dataReceived(mealDetail.body()!!)}
            }
        }
    }

    private fun dataReceived(meal: MealDetailResponse) {
        val mealObj = meal.meals[0] //Tiene sólo un objeto el Array !!!!
        val imageUrl = mealObj.image
        Picasso.get().load(imageUrl).into(binding.ivMealD)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}