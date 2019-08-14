package com.example.elmenuschallenge.utils

import android.content.Context
import android.net.ConnectivityManager
import java.lang.ref.WeakReference


@Suppress("DEPRECATION")
class NetworkUtils
{
    fun checkNetworkConnection(context: WeakReference<Context>): Boolean
    {
        val connectivityManager = context.get()!!.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}