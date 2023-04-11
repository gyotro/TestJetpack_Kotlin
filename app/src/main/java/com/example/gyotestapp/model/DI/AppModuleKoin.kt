package com.example.gyotestapp.model.DI

import android.util.Log
import com.example.gyotestapp.model.MealsRepo
import com.example.gyotestapp.model.api.MealsApi
import com.example.gyotestapp.model.api.MealsWebService
import com.example.gyotestapp.ui.meals.GyoTestAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    Log.d("Creatiing Modules..", "MealsWebService")
    single<MealsApi> {
        MealsWebService()
    }
    Log.d("Creatiing Modules..", "MealsRepo")
    // con get() gli passiamo l'istanza di MealsWebService creata prima
    single {
        MealsRepo(get())
    }
    Log.d("Creatiing Modules..", "viewModel")
    viewModel { GyoTestAppViewModel(get()) }
}