package com.eriksargsyan.testtaskusers.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eriksargsyan.testtaskusers.model.domain.User
import com.eriksargsyan.testtaskusers.model.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val liveData: MutableLiveData<List<User>> by lazy { MutableLiveData<List<User>>() }

    private lateinit var sharedPref: UserSharedPref

    private fun getAllUsersFromNet() {
        this.viewModelScope.launch {
            liveData.value = userRepository.cacheAndGet()
        }
    }

    private fun getAllUsers() {
        this.viewModelScope.launch {
            liveData.value = userRepository.getUsers()
        }
    }

    fun swipeRefresh(context: Context, view: View) {
        val connectivityManager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            getAllUsersFromNet()
        } else {
            Snackbar.make(view, "No internet connection", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun checkDatabaseAfterStart(context: Context) {
        sharedPref = UserSharedPref(context)
        if (!sharedPref.checkCacheDb()) {
            getAllUsersFromNet()
            sharedPref.addUsersDb()
        } else {
            getAllUsers()
        }
    }


}