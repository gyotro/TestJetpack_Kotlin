package com.example.gyotestapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val veryLightGreen = Color(0x9932CD32)
val veryLightGray = Color(0x60DCDCDC)

// se si vogliono utilizzare i colori fuori dal package ui.theme, va creata una variabile getter
// va creata una extension function su Colors
val Colors.lightGreen: Color
    @Composable
    get() = veryLightGreen



