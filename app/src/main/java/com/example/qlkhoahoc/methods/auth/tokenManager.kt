package com.example.qlkhoahoc.methods.auth

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREF_NAME = "AuthToken"
    private const val KEY_TOKEN = "token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context?): String? {
        return context?.let { getSharedPreferences(it).getString(KEY_TOKEN, null) }
    }

    fun clearToken(context: Context) {
        val editor = getSharedPreferences(context).edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
    }
}
