package com.eriksargsyan.testtaskusers.viewmodel

import com.eriksargsyan.testtaskusers.model.net.UserNet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("users.json")
    suspend fun getUsers(
        @Query("alt") altKey: String = UserService.alt,
        @Query("token") tokenKey: String = UserService.token
    ): List<UserNet>

}