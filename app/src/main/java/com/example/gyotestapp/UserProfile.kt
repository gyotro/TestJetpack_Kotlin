package com.example.gyotestapp
data class UserProfile (
    val userId: Int,
    val name: String,
    val status: Boolean,
    val drawableUrl: String)

val userProfileList = mutableListOf<UserProfile>(
    UserProfile(name = "Hisagi Shuhei",
        userId = 0,
        status = true,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn4t4DTGwP3ucBI0gLh8fien2ZIovaCoPvaVK6Dwu-3Dvj3CVN-U5XduLxR0g6wxNIuiw&usqp=CAU"),
    UserProfile(name = "Grimmjow",
        status = false,
        userId = 1,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDz2u29s1pWZr53qkIwJHz0MZOcQyR5MJZvw&usqp=CAU"),
    UserProfile(name = "Hisagi Shuhei",
        status = true,
        userId = 2,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn4t4DTGwP3ucBI0gLh8fien2ZIovaCoPvaVK6Dwu-3Dvj3CVN-U5XduLxR0g6wxNIuiw&usqp=CAU"),
    UserProfile(name = "Grimmjow",
        status = false,
        userId = 3,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDz2u29s1pWZr53qkIwJHz0MZOcQyR5MJZvw&usqp=CAU"),
    UserProfile(name = "Hisagi Shuhei",
        status = true,
        userId = 4,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn4t4DTGwP3ucBI0gLh8fien2ZIovaCoPvaVK6Dwu-3Dvj3CVN-U5XduLxR0g6wxNIuiw&usqp=CAU"),
    UserProfile(name = "Grimmjow",
        status = false,
        userId = 5,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDz2u29s1pWZr53qkIwJHz0MZOcQyR5MJZvw&usqp=CAU"),
    UserProfile(name = "Hisagi Shuhei",
        status = true,
        userId = 6,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSn4t4DTGwP3ucBI0gLh8fien2ZIovaCoPvaVK6Dwu-3Dvj3CVN-U5XduLxR0g6wxNIuiw&usqp=CAU"),
    UserProfile(name = "Grimmjow",
        status = false,
        userId = 7,
        drawableUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDz2u29s1pWZr53qkIwJHz0MZOcQyR5MJZvw&usqp=CAU")

)

