package com.eriksargsyan.testtaskusers.screen.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.eriksargsyan.testtaskusers.model.network.SettingsStorage
import com.eriksargsyan.testtaskusers.model.repository.UserRepository

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val settingsStorage: SettingsStorage,
) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState.Loading)
    val state: StateFlow<UserState> = _state

    init {
        viewModelScope.launch {
            if (settingsStorage.isDatabaseExist()) {
                fetchUsersFromCache()
            } else {
                fetchUsersFromNetwork()
                settingsStorage.setDatabaseExistFlag(true)
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            fetchUsersFromNetwork()
        }
    }

    private suspend fun fetchUsersFromCache() {
        _state.value = UserState.Success(userRepository.getUsers())
    }

    private suspend fun fetchUsersFromNetwork() {
        _state.value = if (settingsStorage.hasNetworkAccess()) {
            try {
                UserState.Success(userRepository.cacheAndGet())
            } catch (e: IllegalStateException) {
                UserState.Error
            }
        } else {
            UserState.Error
        }
    }

}