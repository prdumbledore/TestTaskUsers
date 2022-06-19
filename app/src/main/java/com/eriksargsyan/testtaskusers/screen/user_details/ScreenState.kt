package com.eriksargsyan.testtaskusers.screen.user_details

import com.eriksargsyan.testtaskusers.model.data.domain.User

sealed interface UserWithFriendsState {
    object Loading : UserWithFriendsState
    data class Success(val user: User, val friends: List<User>) : UserWithFriendsState
}