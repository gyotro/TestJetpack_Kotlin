package com.example.gyotestapp.model

import com.example.gyotestapp.model.api.MealsWebService
import com.example.gyotestapp.model.response.MealsCategoriesResponse

class MealsRepo(private val mealsApi: MealsWebService = MealsWebService()) {
    fun getMeals(): MealsCategoriesResponse? {
        return mealsApi.getMeals().execute().body() // Bad Practice: blocking call
    }
}