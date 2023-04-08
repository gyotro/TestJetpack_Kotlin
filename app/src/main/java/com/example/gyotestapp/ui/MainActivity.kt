package com.example.gyotestapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gyotestapp.ui.meals.MealsListScreen
import com.example.gyotestapp.ui.theme.GyoTestAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = {
            // carichiamo il template custom
            GyoTestAppTheme() {
                //DestinationsNavHost(navGraph = NavGraphs.root)
                MealsListScreen()
            }
        })
    }
}
