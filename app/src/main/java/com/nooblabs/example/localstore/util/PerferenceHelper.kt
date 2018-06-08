package com.nooblabs.example.localstore.util

import android.content.Context

object PerferenceHelper {

    val DB_INITIALIZED = "db_init"
    val PREF_NAME = "pref_file"

    fun isDbInitialized(context: Context) =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean(DB_INITIALIZED, false)

    fun dbInit(context: Context){
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.apply {
            putBoolean(DB_INITIALIZED, true)
            apply()
        }
    }

}