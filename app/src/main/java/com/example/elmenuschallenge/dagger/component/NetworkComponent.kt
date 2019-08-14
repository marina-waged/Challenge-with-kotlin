package com.example.elmenuschallenge.dagger.component

import com.example.elmenuschallenge.dagger.module.AppModule
import com.example.elmenuschallenge.dagger.module.NetworkModule
import com.example.elmenuschallenge.view.menusList.MenusListActivity
import javax.inject.Singleton
import dagger.Component


@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class)])
interface NetworkComponent
{
    fun inject(menusListActivity: MenusListActivity)
}