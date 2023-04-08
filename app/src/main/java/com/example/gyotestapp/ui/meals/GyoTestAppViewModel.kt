package com.example.gyotestapp.ui.meals

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gyotestapp.model.MealsRepo
import com.example.gyotestapp.model.response.MealResponse
import com.example.gyotestapp.model.response.MealsCategoriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GyoTestAppViewModel(private val mealsRepo: MealsRepo = MealsRepo()): ViewModel() {

    /*
     ci sono due modi per lanciare coroutine da una viewModel:
     1) Usare il viewModel Scope -> recommended
     2) creare un Job Custom e lanciare la coroutine attraverso uno scope Custom
     */
    // creazione Job custom per l'opzione 2
    private val mealsJob: Job = Job()

    init {
        Log.d("ViewModelCoroutine", "Before the coroutine")
        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        //  opzione 2
        //scope.launch(Dispatchers.IO) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("ViewModelCoroutine", "Inside the coroutine, before the async Call")
            mealsState.value = getMeals()
            Log.d("ViewModelCoroutine", "Inside the coroutine, after the async Call")
        }
        Log.d("ViewModelCoroutine", "After the coroutine")
    }

    val mealsState = mutableStateOf(emptyList<MealResponse>() )
    suspend fun getMeals(): List<MealResponse> = mealsRepo.getMeals().categories

    // da richiamare quando viene distrutta la viewModel solo da fare per l'opzione 2
    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }

}