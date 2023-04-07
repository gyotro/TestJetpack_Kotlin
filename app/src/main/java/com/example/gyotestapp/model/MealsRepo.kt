package com.example.gyotestapp.model

import com.example.gyotestapp.model.api.MealsWebService
import com.example.gyotestapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepo(private val mealsApi: MealsWebService = MealsWebService()) {
    fun getMeals(successCallback: (response: MealsCategoriesResponse) -> Unit) {
        return mealsApi.getMeals().enqueue(object : Callback<MealsCategoriesResponse> {
            override fun onResponse(call: Call<MealsCategoriesResponse>, response: Response<MealsCategoriesResponse>) {
                if (response.isSuccessful)
                    response.body()?.let { successCallback(it) }
            }

            override fun onFailure(call: Call<MealsCategoriesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}