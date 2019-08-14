package com.example.elmenuschallenge.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elmenuschallenge.model.ItemData


@Dao
interface ItemsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(itemsList: List<ItemData>)

    @Query("SELECT * FROM ItemData WHERE tag_name = :tagName")
    fun getItemsByTagName(tagName: String): List<ItemData>
}