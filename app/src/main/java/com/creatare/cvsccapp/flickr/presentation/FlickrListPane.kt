package com.creatare.cvsccapp.flickr.presentation

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.creatare.cvsccapp.flickr.domain.model.FlickrImage
import com.creatare.cvsccapp.ui.extension.header

@Composable
fun FlickrListPane(
    flickrState: FlickrState,
    lazyGridState: LazyGridState,
    context: Context,
    onImageClicked: (FlickrImage) -> Unit
) {
    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Fixed(3),
        content = {
            header {
                Spacer(Modifier.height(70.dp))
            }
            flickrState.flickrData?.images?.let { images ->
                items(images.size) { index ->
                    FlickrImageItem(
                        images[index],
                        context,
                        onImageClicked
                    )
                }
            }
        }
    )
}