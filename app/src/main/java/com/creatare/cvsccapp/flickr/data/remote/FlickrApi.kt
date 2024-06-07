package com.creatare.cvsccapp.flickr.data.remote

import com.creatare.cvsccapp.flickr.data.remote.dto.FlickrDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchPhotos(
        @Query("tags") tags: String? = null
    ): FlickrDataDto

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/feeds/"
    }
}