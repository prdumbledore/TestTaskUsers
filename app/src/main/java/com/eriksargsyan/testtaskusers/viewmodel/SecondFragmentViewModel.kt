package com.eriksargsyan.testtaskusers.viewmodel

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val mutableLiveData: MutableLiveData<List<User>> by lazy { MutableLiveData<List<User>>() }
        val liveData: LiveData<List<User>>
            get () = mutableLiveData

    private val mutableButtonEvent = MutableLiveData<Intent>()
        val buttonEvent: LiveData<Intent>
            get() = mutableButtonEvent


    fun createFriendsListForCurrentUser(userId: Int) {

        this.viewModelScope.launch {
            val friendsList: MutableList<User> = mutableListOf()
            val currentUser = userRepository.getUser(userId)
            friendsList.add(currentUser)
            for (friend in currentUser.friends) {
                friendsList.add(userRepository.getUser(friend))
            }
            mutableLiveData.value = friendsList
        }
    }

    fun onEmailClick(address: CharSequence) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            val mailto = "mailto:" + arrayOf(address).joinToString(",")
            data = Uri.parse(mailto)
        }
        mutableButtonEvent.value = intent

    }

    fun onPhoneClick(phoneNumber: CharSequence) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            val phoneCall = "tel:" + Regex("""[-()\s]""").replace(phoneNumber, "")
            data = Uri.parse(phoneCall)
        }
        mutableButtonEvent.value = intent
    }

    fun onGeoClick(geo: CharSequence) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:$geo")
        }
        mutableButtonEvent.value = intent
    }



}