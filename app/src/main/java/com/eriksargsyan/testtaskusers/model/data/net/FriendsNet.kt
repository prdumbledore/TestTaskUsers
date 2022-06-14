package com.eriksargsyan.testtaskusers.model.data.net

import com.google.gson.annotations.Expose
import kotlinx.serialization.Serializable

@Serializable
data class FriendsNet(

    @Expose
    val id: Int
)
