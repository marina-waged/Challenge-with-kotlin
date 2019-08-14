package com.example.elmenuschallenge

import android.app.Application
import com.example.elmenuschallenge.dagger.component.DaggerNetworkComponent
import com.example.elmenuschallenge.dagger.component.NetworkComponent
import com.example.elmenuschallenge.dagger.module.AppModule
import com.example.elmenuschallenge.dagger.module.NetworkModule

@Suppress("DEPRECATION")
class ElMenusChallengeApplication : Application()
{
    var networkComponent: NetworkComponent? = null

    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}