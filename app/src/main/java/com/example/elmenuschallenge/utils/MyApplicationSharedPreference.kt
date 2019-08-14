package com.example.elmenuschallenge.utils

import android.content.Context
import android.content.SharedPreferences
import java.lang.ref.WeakReference

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MyApplicationSharedPreference (context : WeakReference<Context>)
{
    companion object {
        private lateinit var sharedPreference: SharedPreferences
        private lateinit var coreSharedPreference: SharedPreferences
    }

    init {

        sharedPreference = context.get()!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        coreSharedPreference = context.get()!!.getSharedPreferences("corePref", Context.MODE_PRIVATE)
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreference.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun saveStringOnCore(key: String, value: String) {
        coreSharedPreference.edit().putString(key, value).apply()
    }

    fun getStringFromCore(key: String): String? {
        return coreSharedPreference.getString(key, "")
    }

    fun saveBooleanOnCore(key: String, value: Boolean) {
        coreSharedPreference.edit().putBoolean(key, value).apply()
    }

    fun getBooleanOnCore(key: String): Boolean? {
        return coreSharedPreference.getBoolean(key, false)
    }

    fun saveString(key: String, value: String) {
        sharedPreference.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreference.getString(key, "")
    }

    fun logOut() {
        sharedPreference.edit().clear().apply()
    }

}