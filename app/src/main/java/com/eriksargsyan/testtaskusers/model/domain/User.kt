package com.eriksargsyan.testtaskusers.model.domain

import com.eriksargsyan.testtaskusers.R


data class User(
    val id: Int,
    val isActive: Boolean,
    val age: Int,
    val eyeColor: EyeColor,
    val name: String,
    val gender: String,
    val company: String,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val friends: List<Int>,
    val favoriteFruit: Fruit
)

enum class EyeColor(val id: Int, val str: String) {
    COLOR_BROWN(R.drawable.brown_eyes, "brown"),
    COLOR_GREEN(R.drawable.green_eyes, "green"),
    COLOR_BLUE(R.drawable.blue_eyes, "blue")
}

enum class Fruit(val id: Int, val str: String) {
    APPLE(R.drawable.apple, "apple"),
    BANANA(R.drawable.banana, "banana"),
    STRAWBERRY(R.drawable.strawberry, "strawberry")
}
