package com.example.weatherapp.data.utils

import android.content.Context
import android.content.SharedPreferences


object PrefsUtil {
private var sharedPrefs: SharedPreferences? = null
    private const val SHARE_PREFS_NAME="SharedPrefs"
    private const val MY_CLOTHE="keyClothe"
    private val myDataKey = "MY_DATA"

    fun initPrefsUtil(context: Context){
        sharedPrefs =context.getSharedPreferences(SHARE_PREFS_NAME,Context.MODE_PRIVATE)
    }

    var clothe: Int
        get()= sharedPrefs!!.getInt(MY_CLOTHE, 0)
        set(value) = sharedPrefs!!.edit().putInt(MY_CLOTHE, value).apply()

    var date: String?
        get() = sharedPrefs?.getString(myDataKey, "")
        set(value) {
            sharedPrefs?.edit()?.putString(myDataKey, value)?.apply()
        }
}