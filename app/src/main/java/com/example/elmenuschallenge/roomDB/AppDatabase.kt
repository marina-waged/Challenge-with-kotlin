package com.example.elmenuschallenge.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elmenuschallenge.model.ItemData
import com.example.elmenuschallenge.model.TagData
import com.example.elmenuschallenge.roomDB.dao.ItemsDao
import com.example.elmenuschallenge.roomDB.dao.TagsDao
import java.lang.ref.WeakReference


@Database(entities = [(TagData::class), (ItemData::class)], version = 5)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun tagsDao() : TagsDao
    abstract fun itemsDao() : ItemsDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: WeakReference<Context>): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.get()!!.applicationContext, AppDatabase::class.java, "menus_db").build()
                }
            }
            return INSTANCE
        }
    }
}