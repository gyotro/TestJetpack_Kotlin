package com.example.gyotestapp.ui.meals

import androidx.lifecycle.ViewModel
import com.example.gyotestapp.model.MealsRepo
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.model.response.MealsCategoriesResponse

class GyoTestAppViewModel(private val mealsRepo: MealsRepo = MealsRepo()): ViewModel() {
    fun getMeals(callback: (responseCallback: MealsCategoriesResponse) -> Unit) = mealsRepo.getMeals(callback)

}