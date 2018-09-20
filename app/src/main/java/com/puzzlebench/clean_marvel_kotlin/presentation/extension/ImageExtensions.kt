package com.puzzlebench.clean_marvel_kotlin.presentation.extension

import android.widget.ImageView
import com.puzzlebench.cmk.domain.model.Thumbnail
import com.squareup.picasso.Picasso

fun ImageView.getImageByUrl(url: String) {
    Picasso.with(context).load(url).fit().centerCrop().into(this)
}

fun Thumbnail.toPathString() : String
{
    return "${this.path}.${this.extension}"
}