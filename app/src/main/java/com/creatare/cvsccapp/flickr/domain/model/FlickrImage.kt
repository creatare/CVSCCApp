package com.creatare.cvsccapp.flickr.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlickrImage(
    val author: String,
    val authorId: String,
    val dateTaken: String,
    val description: String,
    val link: String,
    val media: Media,
    val published: String,
    val tags: String,
    val title: String
): Parcelable
