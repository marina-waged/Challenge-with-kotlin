package com.example.elmenuschallenge.view.menusList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elmenuschallenge.R
import com.example.elmenuschallenge.model.TagData
import com.example.elmenuschallenge.utils.ImageLoader
import kotlinx.android.synthetic.main.tag_row.view.*
import java.lang.ref.WeakReference


class TagsListAdapter (private var context : WeakReference<Context>, private var tagsList : ArrayList<TagData>
                       , private var listener: TagsListener)
    : RecyclerView.Adapter<TagsListAdapter.TagsHolder>()
{
    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder
    {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.tag_row, parent, false)

        return TagsHolder(view)
    }

    override fun getItemCount(): Int
    {
        return tagsList.size
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int)
    {
        val tagObj = tagsList[position]
        holder.tagName.text = tagObj.getTagName()
        ImageLoader.load(context, tagObj.getPhotoURL()!!, holder.tagIcon)

        holder.tagItem.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
        }

        if(selectedIndex == position)
        {
            holder.tagItem.setBackgroundResource(R.drawable.bg_tag_selected)
            listener.tagClick(tagObj.getTagName()!!)
        }else
            holder.tagItem.setBackgroundResource(R.drawable.bg_tag_un_selected)
    }

    inner class TagsHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val tagName = itemView.tag_name!!
        val tagIcon = itemView.tag_img!!
        val tagItem = itemView.tag_item!!
    }
}