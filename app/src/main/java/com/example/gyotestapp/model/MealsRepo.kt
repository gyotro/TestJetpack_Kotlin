package com.example.gyotestapp.model

import com.example.gyotestapp.model.response.MealsCategoriesResponse

class MealsRepo {
    fun getMeals(): MealsCategoriesResponse = MealsCategoriesResponse(mutableListOf())
}