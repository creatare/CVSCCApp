package com.creatare.cvsccapp.flickr.data.remote.dto

import com.creatare.cvsccapp.flickr.domain.model.FlickrImage
import com.google.gson.annotations.SerializedName

data class ItemDto(
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val link: String,
    val media: MediaDto,
    val published: String,
    val tags: String,
    val title: String
) {

    fun toFlickrImage(): FlickrImage {
        return FlickrImage(
            author,
            authorId,
            dateTaken,
            description,
            link,
            media = media.toMedia(),
            published,
            tags,
            title
        )
    }

}