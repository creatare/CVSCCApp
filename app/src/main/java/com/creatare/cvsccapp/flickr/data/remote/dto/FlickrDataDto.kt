package com.creatare.cvsccapp.flickr.data.remote.dto

import com.creatare.cvsccapp.flickr.domain.model.FlickrData

data class FlickrDataDto(
    val description: String,
    val generator: String,
    val items: List<ItemDto>,
    val link: String,
    val modified: String,
    val title: String
) {
    fun toFlickrData(): FlickrData {
        return FlickrData(
            description,
            generator,
            images = items.map { it.toFlickrImage()  },
            link,
            modified,
            title
        )
    }
}
