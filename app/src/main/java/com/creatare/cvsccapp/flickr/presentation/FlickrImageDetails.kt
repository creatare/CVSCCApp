package com.creatare.cvsccapp.flickr.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.creatare.cvsccapp.R
import com.creatare.cvsccapp.flickr.domain.model.FlickrImage
import com.creatare.cvsccapp.ui.extension.shareImage

@Composable
fun FlickrImageDetails(
    flickrImage: FlickrImage,
    context: Context,
) {

    val imagePainter: AsyncImagePainter = if (flickrImage.media.mImageUrl.isBlank()) {
        painterResource(R.drawable.silverbg) as AsyncImagePainter
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

    Column(
        modifier = Modifier.fillMaxWidth()
    )
    {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = flickrImage.title,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Black),
            painter = imagePainter,
            contentDescription = flickrImage.title
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Width: ${imagePainter.intrinsicSize.width} Height: ${imagePainter.intrinsicSize.height}",
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            text = "Author: ${flickrImage.author} (${flickrImage.authorId})",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Button(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = {
                val state = imagePainter.state as? AsyncImagePainter.State.Success
                val drawable = state?.result?.drawable
                drawable?.let {
                    context.shareImage(title = context.getString(R.string.share_image_via),
                        it,
                        flickrImage.dateTaken,
                        flickrImage.title)
                }
            }) {
            Text(text = stringResource(id = R.string.share_button_text))
        }
    }
}