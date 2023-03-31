package com.example.gyotestapp
data class UserProfile (val name: String,
                       val status: Boolean,
                       val drawable: Int)

val userProfileList = mutableListOf<UserProfile>(
    UserProfile(name = "Hisagi Shuhei", status = true, drawable = R.drawable.hisag),
    UserProfile(name = "Grimmjow", status = false, drawable = R.drawable.grim)
)
