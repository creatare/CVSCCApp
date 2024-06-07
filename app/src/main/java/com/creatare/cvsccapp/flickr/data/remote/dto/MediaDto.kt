package com.creatare.cvsccapp.flickr.data.remote.dto

import com.creatare.cvsccapp.flickr.domain.model.Media

data class MediaDto(
    val m: String
) {
    fun toMedia(): Media {
        return Media(
            mImageUrl = m
        )
    }
}