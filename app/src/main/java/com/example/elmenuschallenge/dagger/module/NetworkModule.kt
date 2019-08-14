package com.example.elmenuschallenge.dagger.module

import com.example.elmenuschallenge.api.MenusListApis
import com.example.elmenuschallenge.utils.BASE_URL
import com.example.elmenuschallenge.view.menusList.MenusListInteractor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {
    private var okHttpClient: OkHttpClient? = null
    private var retrofitInstance: Retrofit? = null

    private var menusListApis : MenusListApis ?= null


    //----------------------------- Basic Providers ----------------------

    @Provides
    @Singleton
    fun provideOkayHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okHttpClient = OkHttpClient.Builder()
                    .readTimeout(70, TimeUnit.SECONDS)
                    .connectTimeout(70, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()
        }
        return okHttpClient!!
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        if (retrofitInstance == null) {
            val retrofit = Retrofit.Builder()
                    .client(provideOkayHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            retrofitInstance = retrofit.build()
        }
        return retrofitInstance!!
    }

    //-------------------------------- api Providers ------------------------------

    @Provides
    @Singleton
    fun provideMenusListApi() : MenusListApis
    {
        if(menusListApis == null)
            menusListApis = provideRetrofit().create(MenusListApis::class.java)
        return menusListApis!!
    }

    @Provides
    @Singleton
    fun provideMenusListInteractor() : MenusListInteractor
    {
        return MenusListInteractor(provideMenusListApi())
    }

}
