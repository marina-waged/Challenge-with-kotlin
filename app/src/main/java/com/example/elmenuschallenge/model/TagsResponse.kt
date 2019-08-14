package com.example.elmenuschallenge.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TagsResponse
{
    @SerializedName("tags")
    @Expose
    private var tags: List<TagData>? = null

    fun getTags(): List<TagData>? {
        return tags
    }

    fun setTags(tags: List<TagData>) {
        this.tags = tags
    }
}