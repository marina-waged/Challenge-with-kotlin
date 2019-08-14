package com.example.elmenuschallenge.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.target.Target
import java.lang.ref.WeakReference


class ImageLoader
{
    companion object
    {
        fun load(context: WeakReference<Context>, imageURl: String, imageView: ImageView)
        {
            Glide.with(context.get()!!)
                .load(GlideUrl(imageURl, LazyHeaders.Builder().build())).centerCrop()
                .thumbnail(0.1f).listener(object : RequestListener<Drawable>
                {
                    override fun onLoadFailed(e: GlideException?, model: Any?,
                                              target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any,
                                                 target: Target<Drawable>,
                                                 dataSource: DataSource,
                                                 isFirstResource: Boolean): Boolean {
                        return false
                    }
                }).into(imageView)
        }
    }

}