package com.hola.appcountries.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hola.appcountries.databinding.ActivityDetailMealBinding
import com.hola.appcountries.domain.model.MealDetailItem
import com.hola.appcountries.ui.viewmodel.MealDataViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMealActivity : AppCompatActivity() {

    //Constante para el extra del activity
    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailMealBinding
    private val mealDataViewModel: MealDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recuperación de la ID
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()  //Al poder dar null el ID que mande en ese caso un vacío para que no haya error
        getMealInformation(id)
    }

    private fun getMealInformation(id: String) {
        mealDataViewModel.getDetailsMeal(id)
        runOnUiThread {
            mealDataViewModel.detailsDataModel.observe(this@DetailMealActivity){detailsResponse ->
                detailsResponse?.let { dataReceived(detailsResponse) }
            }
        }
    }

    private fun dataReceived(meal: List<MealDetailItem>) {
        //Tiene sólo un objeto el Array !!!!

        binding.tvTitle.text = meal[0].name
        val imageUrl = meal[0].image
        Picasso.get().load(imageUrl).into(binding.ivMealD)
        val category = meal[0].category
        binding.tvSubtitleCategory.text = category
        val area = meal[0].area
        binding.tvArea.text = area
        val instructions = meal[0].instructions
        binding.tvInstructions.text = instructions


    }

}