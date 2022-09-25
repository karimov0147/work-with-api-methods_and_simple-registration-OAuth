package com.quattroSoft.contactAppV4.data.source.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalStorage @Inject constructor(private val preferences: SharedPreferences){

    fun getToken() = preferences.getString("token" , null)

    fun setToken(token : String?){
        val editor = preferences.edit()
        editor.putString("token" , token)
        editor.apply()
    }
}