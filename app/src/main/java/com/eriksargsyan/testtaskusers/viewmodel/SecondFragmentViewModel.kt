package com.eriksargsyan.testtaskusers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val liveData: MutableLiveData<List<User>> by lazy { MutableLiveData<List<User>>() }

    fun createFriendsListForCurrentUser(userId: Int) {

        this.viewModelScope.launch {
            val friendsList: MutableList<User> = mutableListOf()
            val currentUser = userRepository.getUser(userId)
            friendsList.add(currentUser)
            for (friend in currentUser.friends) {
                friendsList.add(userRepository.getUser(friend))
            }
            liveData.value = friendsList
        }
    }



}