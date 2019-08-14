package com.example.elmenuschallenge.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(foreignKeys = [ForeignKey(
    entity = TagData::class,
    parentColumns = ["tagName"],
    childColumns = ["tag_name"],
    onDelete = ForeignKey.CASCADE
)], indices = [(Index(value = ["tag_name"]))])
class ItemData
{
    @PrimaryKey(autoGenerate = true)
    private var dbId =0

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @ColumnInfo(name = "item_image")
    @SerializedName("photoUrl")
    @Expose
    private var photoUrl: String? = null

    @ColumnInfo(name = "item_description")
    @SerializedName("description")
    @Expose
    private var description: String? = null

    @ColumnInfo(name = "tag_name")
    private var itemTagName: String ?= null

    fun getDbId(): Int? {
        return dbId
    }

    fun setDbId(dbId: Int?) {
        this.dbId = dbId!!
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getPhotoUrl(): String? {
        return photoUrl
    }

    fun setPhotoUrl(photoUrl: String) {
        this.photoUrl = photoUrl
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getItemTagName(): String? {
        return itemTagName
    }

    fun setItemTagName(itemTagName: String) {
        this.itemTagName = itemTagName
    }

}