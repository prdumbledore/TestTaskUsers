package com.eriksargsyan.testtaskusers.model.data.domain

import com.eriksargsyan.testtaskusers.R


data class User(
    val id: Int,
    val isActive: Boolean,
    val age: Int,
    val eyeColor: EyeColor,
    val name: String,
    val gender: Gender,
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
    COLOR_BLUE(R.drawable.blue_eyes, "blue");

    companion object {
        fun fromStr(str: String): EyeColor {
            for (eyeColor in values()) {
                if (str == eyeColor.str) {
                    return eyeColor
                }
            }
            throw IllegalArgumentException("Not implemented eye color")
        }
    }
}

enum class Fruit(val id: Int, val str: String) {
    APPLE(R.drawable.apple, "apple"),
    BANANA(R.drawable.banana, "banana"),
    STRAWBERRY(R.drawable.strawberry, "strawberry");

    companion object {
        fun fromStr(str: String): Fruit {
            for (fruit in values()) {
                if (str == fruit.str) {
                    return fruit
                }
            }
            throw IllegalArgumentException("Not implemented fruit")
        }
    }
}

enum class Gender(val id: Int, val str: String) {
    MALE(R.drawable.male, "male"),
    FEMALE(R.drawable.female, "female");

    companion object {
        fun fromStr(str: String): Gender {
            for (gender in values()) {
                if (str == gender.str) {
                    return gender
                }
            }
            throw IllegalArgumentException("Not implemented gender")
        }
    }
}