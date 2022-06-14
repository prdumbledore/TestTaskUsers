package com.eriksargsyan.testtaskusers.model.net

import com.eriksargsyan.testtaskusers.model.data.net.UserNet
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("users.json")
    suspend fun getUsers(
        @Query("alt") altKey: String = UserService.alt,
        @Query("token") tokenKey: String = UserService.token
    ): List<UserNet>

}