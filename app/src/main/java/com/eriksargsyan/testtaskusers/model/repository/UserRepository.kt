package com.eriksargsyan.testtaskusers.model.repository

import com.eriksargsyan.testtaskusers.model.domain.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUser(id: Int): User

    suspend fun cacheAndGet():  List<User>

}