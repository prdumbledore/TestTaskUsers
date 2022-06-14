package com.eriksargsyan.testtaskusers.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eriksargsyan.testtaskusers.model.data.database.UserDB
import com.eriksargsyan.testtaskusers.util.FriendsConvert

@Database(entities = [UserDB::class], version = 1)
@TypeConverters(FriendsConvert::class)
abstract class UserRoomDatabase: RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {

       const val DATABASE_NAME = "user_db"
    }

}