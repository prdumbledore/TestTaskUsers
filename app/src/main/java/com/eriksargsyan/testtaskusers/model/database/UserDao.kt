package com.eriksargsyan.testtaskusers.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM user_db ORDER BY id ASC")
    suspend fun getAllUsers(): List<UserDB>

    @Query("SELECT * FROM user_db WHERE id = :id")
    suspend fun getById( id: Int): UserDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDB)

    @Query("DELETE FROM user_db")
    suspend fun deleteAllUsers()

}