package com.eriksargsyan.testtaskusers.model.net

import com.google.gson.annotations.Expose
import kotlinx.serialization.Serializable

@Serializable
data class UserNet(
    @Expose
    val id: Int,
    @Expose
    val isActive: Boolean,
    @Expose
    val age: Int,
    @Expose
    val eyeColor: String,
    @Expose
    val name: String,
    @Expose
    val gender: String,
    @Expose
    val company: String,
    @Expose
    val email: String,
    @Expose
    val phone: String,
    @Expose
    val address: String,
    @Expose
    val about: String,
    @Expose
    val registered: String,
    @Expose
    val latitude: Double,
    @Expose
    val longitude: Double,
    @Expose
    val friends: List<FriendsNet>,
    @Expose
    val favoriteFruit: String
)
