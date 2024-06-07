package com.creatare.cvsccapp.flickr.presentation

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.creatare.cvsccapp.flickr.domain.model.FlickrImage

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun FlickrDetailPane(
    navigator: ThreePaneScaffoldNavigator<Any>,
    context: Context,
    onBackHandler: () -> Unit
) {
    val image = navigator.currentDestination?.content as? FlickrImage
    image?.let { flickImage ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlickrImageDetails(
                flickImage,
                context)
        }
    }
    BackHandler(navigator.canNavigateBack()) {
        onBackHandler()
    }
}