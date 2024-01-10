package com.tasty.recipesapp.ui.profile

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserInfo(name: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_NAME, "")
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, "")
    }

    companion object {
        private const val PREFS_NAME = "MyAppPrefs"
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"

        private var instance: PreferencesManager? = null

        @Synchronized
        fun getInstance(context: Context): PreferencesManager {
            if (instance == null) {
                instance = PreferencesManager(context.applicationContext)
            }
            return instance as PreferencesManager
        }
    }
}
