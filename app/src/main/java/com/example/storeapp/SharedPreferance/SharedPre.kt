package com.example.storeapp.SharedPreferance

import android.content.Context
import android.content.SharedPreferences
import com.example.storeapp.helpers.C

class SharedPre {

    companion object {
        lateinit var sharedPreferences: SharedPreferences


        fun setCheckOnLanguage(context: Context, lang: String) {
            sharedPreferences = context.getSharedPreferences(C.LANGUAGE, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit().putString(C.LANGUAGE, lang)
            editor.apply()
        }

        fun getCheckOnLanguage(context: Context):String {
            sharedPreferences = context.getSharedPreferences(C.LANGUAGE, Context.MODE_PRIVATE)
            return sharedPreferences.getString(C.LANGUAGE, C.ENGLISH)!!
        }

    }
}