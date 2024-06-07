package com.creatare.cvsccapp.flickr.domain.use_case

import Resource
import com.creatare.cvsccapp.flickr.domain.model.FlickrData
import com.creatare.cvsccapp.flickr.domain.repository.FlickrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchPhotos(
    private val repository: FlickrRepository
) {
    operator fun invoke(tags: String): Flow<Resource<FlickrData>> {
        if (tags.isBlank()) {
            return flow { }
        }
          return repository.searchPhotos(tags)
    }
}