package com.example.gyotestapp.ui.meals

import androidx.lifecycle.ViewModel
import com.example.gyotestapp.model.MealsRepo
import com.example.gyotestapp.model.response.MealResponse

class GyoTestAppViewModel(private val mealsRepo: MealsRepo = MealsRepo()): ViewModel() {
    fun getMeals(): List<MealResponse> = mealsRepo.getMeals().categories
}