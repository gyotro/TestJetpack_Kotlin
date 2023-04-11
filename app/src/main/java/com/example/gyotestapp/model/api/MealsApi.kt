package com.example.gyotestapp.model.api

import com.example.gyotestapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MealsWebService: MealsApi {
    private lateinit var api: MealsApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }
    override suspend fun getMeals(): MealsCategoriesResponse = api.getMeals()

}
interface MealsApi {
    @GET("categories.php") // indichiamo il path
    suspend fun getMeals(): MealsCategoriesResponse
}