package com.app.flipprteachear.utillsa

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences



class PreferenceManager  ( context: Context) {
    companion object {
        const val userId = "user_id"
        const val name = "FlipperTeacher"
        const val profilePhoto = "profilePhoto"
        const val permissions = "PERMISSION"
    }

    var sharedPreferences: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)


    fun putValueString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getValueString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

}