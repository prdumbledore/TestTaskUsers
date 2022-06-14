package com.eriksargsyan.testtaskusers.util

import androidx.room.TypeConverter

class FriendsConvert {

    @TypeConverter
    fun toFriends (friends: String): List<Int> {
        return friends.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromFriends(friends: List<Int>): String {
        return friends.joinToString(separator = ",")
    }
}