package com.creatare.cvsccapp.flickr.presentation

import com.creatare.cvsccapp.flickr.domain.model.FlickrData

data class FlickrState(
    val flickrData: FlickrData? = null,
    val isLoading: Boolean = false
)
