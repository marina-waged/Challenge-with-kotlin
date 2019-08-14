package com.example.elmenuschallenge.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elmenuschallenge.model.TagData


@Dao
interface TagsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTags(tags: List<TagData>)

    @get:Query("SELECT * FROM tagdata")
    val tagsList: List<TagData>
}