package com.eriksargsyan.testtaskusers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val liveData: MutableLiveData<List<User>> by lazy { MutableLiveData<List<User>>() }

    fun getAllUsersFromNet() {
        this.viewModelScope.launch {
            liveData.value = userRepository.cacheAndGet()
        }
    }

    fun getAllUsers() {
        this.viewModelScope.launch {
            liveData.value = userRepository.getUsers()
        }
    }


}