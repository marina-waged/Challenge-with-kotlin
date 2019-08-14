package com.example.elmenuschallenge.view.menusDetails

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.elmenuschallenge.R
import com.example.elmenuschallenge.utils.ImageLoader
import kotlinx.android.synthetic.main.details_activity.*
import java.lang.ref.WeakReference


class DetailsActivity : AppCompatActivity()
{
    private var itemName = ""
    private var itemImageUrl = ""
    private var itemDescription = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        itemName = intent.getStringExtra("itemName")!!
        itemImageUrl = intent.getStringExtra("itemImageUrl")!!
        itemDescription = intent.getStringExtra("itemDescription")!!

        setToolbar(itemName)
        ImageLoader.load(WeakReference(this), itemImageUrl, item_image)
        item_dec.text = itemDescription
    }

    private fun setToolbar(@NonNull title: String) {
        setSupportActionBar(myToolbar)
        collapsingToolbarLayout.title = title
    }
}