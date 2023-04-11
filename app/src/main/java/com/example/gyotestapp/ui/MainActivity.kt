package com.example.gyotestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gyotestapp.ui.meals.MealsListScreen
import com.example.gyotestapp.ui.meals.NavGraphs
import com.example.gyotestapp.ui.theme.GyoTestAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            // carichiamo il template custom
            GyoTestAppTheme() {
                Log.d("Starting App", "Before Destination Nav Host")
                DestinationsNavHost(navGraph = NavGraphs.root)
                //MealsListScreen()
            }
        })
    }
}
