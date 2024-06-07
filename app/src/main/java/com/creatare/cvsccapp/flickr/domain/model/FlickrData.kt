package com.creatare.cvsccapp.flickr.domain.model

data class FlickrData(
    val description: String,
    val generator: String,
    val images: List<FlickrImage>,
    val link: String,
    val modified: String,
    val title: String
)
