package com.eriksargsyan.testtaskusers.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.eriksargsyan.testtaskusers.model.data.database.EyeColorConverter
import com.eriksargsyan.testtaskusers.model.data.database.FriendsConverter
import com.eriksargsyan.testtaskusers.model.data.database.FruitConverter
import com.eriksargsyan.testtaskusers.model.data.database.GenderConverter
import com.eriksargsyan.testtaskusers.model.data.database.UserDB

@Database(entities = [UserDB::class], version = 1)
@TypeConverters(
    FriendsConverter::class,
    FruitConverter::class,
    EyeColorConverter::class,
    GenderConverter::class
)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}