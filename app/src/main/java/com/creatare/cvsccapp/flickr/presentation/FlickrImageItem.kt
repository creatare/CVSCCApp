package com.creatare.cvsccapp.flickr.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.creatare.cvsccapp.R
import com.creatare.cvsccapp.flickr.domain.model.FlickrImage

@Composable
fun FlickrImageItem(
    flickrImage: FlickrImage,
    context: Context,
    onImageClicked: (FlickrImage) -> Unit
) {
    val painter = if (flickrImage.media.mImageUrl.isBlank()) {
        painterResource(R.drawable.silverbg)
    } else {
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                .data(flickrImage.media.mImageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .error(R.drawable.silverbg)
                .placeholder(R.drawable.silverbg)
                .build()
        )
    }

    Image(
        modifier = Modifier
            .clickable {
                onImageClicked(flickrImage)
            }
            .padding(2.dp)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = flickrImage.title
    )
}