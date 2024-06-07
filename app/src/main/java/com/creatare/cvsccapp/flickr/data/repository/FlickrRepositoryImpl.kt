package com.creatare.cvsccapp.flickr.data.repository

import Resource
import com.creatare.cvsccapp.flickr.data.remote.FlickrApi
import com.creatare.cvsccapp.flickr.domain.model.FlickrData
import com.creatare.cvsccapp.flickr.domain.repository.FlickrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class FlickrRepositoryImpl(
    private val api: FlickrApi
): FlickrRepository {

    override fun searchPhotos(tags: String): Flow<Resource<FlickrData>> = flow {
        emit(Resource.Loading())
        try {
            val flickerData = api.searchPhotos(tags).toFlickrData()
            emit(Resource.Success(flickerData))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.",
                data = null
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Server unreachable, please check your internet connection.",
                data = null
            ))
        }
    }
}