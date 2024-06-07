package com.creatare.cvsccapp.flickr.presentation

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun FlickrListDetailsLayout(
    flickrState: FlickrState,
    searchTags: String,
    vm: FlickrViewModel,
    context: Context,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    val inDetailView = rememberSaveable { mutableStateOf(false) }

    val lazyGridState = rememberLazyGridState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigableListDetailPaneScaffold(
            navigator = navigator,
            listPane = {
                FlickrListPane(
                    flickrState,
                    lazyGridState,
                    context,
                ) { flickrImage ->
                    inDetailView.value = true
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail,
                        content = flickrImage
                    )
                }
            },
            detailPane = {
                FlickrDetailPane(
                    navigator,
                    context
                ) {
                    inDetailView.value = false
                    navigator.navigateBack()
                }
            }
        )
        FlickrSearchField(
            searchTags = searchTags,
            isVisible = !inDetailView.value,
            onValueChange = vm::searchFlickrPhotos
        )
    }
}