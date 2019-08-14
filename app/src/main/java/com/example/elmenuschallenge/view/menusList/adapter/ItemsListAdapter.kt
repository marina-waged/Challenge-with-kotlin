package com.example.elmenuschallenge.view.menusList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elmenuschallenge.R
import com.example.elmenuschallenge.model.ItemData
import com.example.elmenuschallenge.utils.ImageLoader
import kotlinx.android.synthetic.main.item_row.view.*
import java.lang.ref.WeakReference


class ItemsListAdapter (private var context : WeakReference<Context>, private var itemList : ArrayList<ItemData>
                        , private var listener: ItemListener)
    : RecyclerView.Adapter<ItemsListAdapter.ItemsHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder
    {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_row, parent, false)

        return ItemsHolder(view)
    }

    override fun getItemCount(): Int
    {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int)
    {
        val itemObj = itemList[position]
        holder.itemName.text = itemObj.getName()
        ImageLoader.load(context, itemObj.getPhotoUrl()!!, holder.itemIcon)

        holder.itemLayout.setOnClickListener {
            listener.clickOnItem(itemObj, holder.itemIcon)
        }
    }

    inner class ItemsHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val itemName = itemView.item_name!!
        val itemIcon = itemView.item_img!!
        val itemLayout = itemView.item_lin!!
    }
}