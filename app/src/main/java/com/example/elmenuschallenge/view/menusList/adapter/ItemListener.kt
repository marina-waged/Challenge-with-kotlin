package com.example.elmenuschallenge.view.menusList.adapter

import android.widget.ImageView
import com.example.elmenuschallenge.model.ItemData

interface ItemListener
{
    fun clickOnItem(itemObject : ItemData, imageView : ImageView)
}