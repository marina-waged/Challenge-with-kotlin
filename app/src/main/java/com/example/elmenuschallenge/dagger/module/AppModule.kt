package com.example.elmenuschallenge.dagger.module

import android.app.Application
import com.example.elmenuschallenge.ElMenusChallengeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private var myAppObject: ElMenusChallengeApplication) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return myAppObject
    }
}