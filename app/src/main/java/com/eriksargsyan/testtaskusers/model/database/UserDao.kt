package com.eriksargsyan.testtaskusers.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eriksargsyan.testtaskusers.model.data.database.UserDB


@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserDB.TABLE_NAME} ORDER BY ${UserDB.ID} ASC")
    suspend fun getAllUsers(): List<UserDB>

    @Query("SELECT * FROM ${UserDB.TABLE_NAME} WHERE ${UserDB.ID} = :id")
    suspend fun getById(id: Int): UserDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserDB>)

    @Query("DELETE FROM ${UserDB.TABLE_NAME}")
    suspend fun deleteAllUsers()

}