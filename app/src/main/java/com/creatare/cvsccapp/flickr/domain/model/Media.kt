package com.creatare.cvsccapp.flickr.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    val mImageUrl: String
): Parcelable

