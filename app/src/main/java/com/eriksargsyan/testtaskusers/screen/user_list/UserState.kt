package com.eriksargsyan.testtaskusers.screen.user_list

import com.eriksargsyan.testtaskusers.model.data.domain.User

sealed interface UserState {
    object Loading : UserState
    data class Success(val users: List<User>) : UserState
    object Error : UserState
}