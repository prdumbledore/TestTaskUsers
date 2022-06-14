package com.eriksargsyan.testtaskusers.model.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eriksargsyan.testtaskusers.model.database.UserRoomDatabase

@Entity(tableName = UserRoomDatabase.DATABASE_NAME)
data class UserDB(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "is_active")
    val isActive: Boolean,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "eye_color")
    val eyeColor: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "about")
    val about: String,

    @ColumnInfo(name = "registered")
    val registered: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "friends")
    val friends: List<Int>,

    @ColumnInfo(name = "favorite_fruit")
    val favoriteFruit: String
)
