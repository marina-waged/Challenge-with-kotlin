package com.example.elmenuschallenge.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemsResponse
{
    @SerializedName("items")
    @Expose
    private var items: List<ItemData>? = null

    fun getItems(): List<ItemData>? {
        return items
    }

    fun setItems(items: List<ItemData>) {
        this.items = items
    }
}