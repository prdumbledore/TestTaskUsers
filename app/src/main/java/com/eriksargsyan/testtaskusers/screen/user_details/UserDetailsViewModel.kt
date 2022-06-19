package com.eriksargsyan.testtaskusers.screen.user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.testtaskusers.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UserWithFriendsState>(UserWithFriendsState.Loading)
    val state: StateFlow<UserWithFriendsState> = _state

    fun fetchUserWithHisFriends(userId: Int) {
        viewModelScope.launch {
            val currentUser = userRepository.getUser(userId)
            val friendsList = currentUser.friends.map { friend -> userRepository.getUser(friend) }
            _state.value = UserWithFriendsState.Success(currentUser, friendsList)
        }
    }

}