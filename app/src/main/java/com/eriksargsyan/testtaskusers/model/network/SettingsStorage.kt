package com.eriksargsyan.testtaskusers.model.network

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.core.content.edit
import dagger.Module
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsStorage @Inject constructor(
    @ApplicationContext private val appContext: Context
) {
    private val sharedPref: SharedPreferences =
        appContext.getSharedPreferences(appContext.packageName, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return sharedPref.getString(TOKEN, "")
    }

    fun saveToken(token: String) {
        sharedPref.edit {
            putString(TOKEN, token)
        }
    }

    fun isDatabaseExist(): Boolean = sharedPref.getBoolean(DB_EXIST, false)

    fun setDatabaseExistFlag(flag: Boolean = true) {
        sharedPref.edit {
            putBoolean(DB_EXIST, flag)
        }
    }

    fun hasNetworkAccess(): Boolean {
        val connectivityManager: ConnectivityManager? =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    companion object {
        private const val TOKEN = "token"
        private const val DB_EXIST = "db_exist"
    }
}