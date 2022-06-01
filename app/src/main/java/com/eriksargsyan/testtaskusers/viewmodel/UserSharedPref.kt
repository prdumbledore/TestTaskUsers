package com.eriksargsyan.testtaskusers.viewmodel

import android.content.Context
import android.content.SharedPreferences


class UserSharedPref (private val context: Context) {

    private val settings: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun checkCacheDb () : Boolean = settings.getBoolean("db_exist", false)

    fun addUsersDb () {
        with(settings.edit()) {
            putBoolean("db_exist", true)
            commit()
        }
    }

}