package com.example.elmenuschallenge.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity
class TagData
{
    @PrimaryKey
    @NonNull
    @SerializedName("tagName")
    @Expose
    private var tagName: String ?= null

    @ColumnInfo(name = "tag_image")
    @SerializedName("photoURL")
    @Expose
    private var photoURL: String ?= null


    fun getTagName(): String? {
        return tagName
    }

    fun setTagName(tagName: String) {
        this.tagName = tagName
    }

    fun getPhotoURL(): String? {
        return photoURL
    }

    fun setPhotoURL(photoURL: String) {
        this.photoURL = photoURL
    }
}