package com.creatare.cvsccapp.flickr.domain.repository

import Resource
import com.creatare.cvsccapp.flickr.domain.model.FlickrData
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {

    fun searchPhotos(tags: String): Flow<Resource<FlickrData>>
}