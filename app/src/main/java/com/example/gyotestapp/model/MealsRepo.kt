package com.example.gyotestapp.model

import com.example.gyotestapp.model.api.MealsWebService
import com.example.gyotestapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepo(private val mealsApi: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategoriesResponse {
        return mealsApi.getMeals()
    }
}