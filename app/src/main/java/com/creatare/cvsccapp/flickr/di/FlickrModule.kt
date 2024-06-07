package com.creatare.cvsccapp.flickr.di

import com.creatare.cvsccapp.flickr.data.remote.FlickrApi
import com.creatare.cvsccapp.flickr.data.repository.FlickrRepositoryImpl
import com.creatare.cvsccapp.flickr.domain.repository.FlickrRepository
import com.creatare.cvsccapp.flickr.domain.use_case.SearchPhotos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlickrModule {

    @Provides
    @Singleton
    fun providersFlickerApi(): FlickrApi {
        return Retrofit.Builder()
            .baseUrl(FlickrApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFlickerRepository(api: FlickrApi): FlickrRepository {
        return FlickrRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesSearchPhotosUseCase(repository: FlickrRepository): SearchPhotos {
        return SearchPhotos(repository)
    }
}