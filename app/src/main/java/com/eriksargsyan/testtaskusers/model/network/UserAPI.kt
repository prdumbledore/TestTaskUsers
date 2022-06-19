package com.eriksargsyan.testtaskusers.model.network

import com.eriksargsyan.testtaskusers.model.data.network.UserNet
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("users.json")
    suspend fun getUsers(
        @Query("alt") alt: String = UserAPIServiceMocks.alt,
        @Query("token") token: String
    ): List<UserNet>

}