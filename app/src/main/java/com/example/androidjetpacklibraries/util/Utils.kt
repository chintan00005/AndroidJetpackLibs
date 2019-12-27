package com.example.androidjetpacklibraries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidjetpacklibraries.R

fun getPlaceHolder(context:Context):CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth=10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri:String, progress:CircularProgressDrawable)
{
    val options = RequestOptions().placeholder(progress).error(R.mipmap.ic_launcher)
    Glide.with(context).
        setDefaultRequestOptions(options).
        load(uri).into(this)
}

@BindingAdapter("load")
fun loadImage(view:ImageView,url:String?)
{
    url?.let { view.loadImage(it, getPlaceHolder(view.context)) }
}