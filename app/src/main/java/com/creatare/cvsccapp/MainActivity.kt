package com.creatare.cvsccapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.creatare.cvsccapp.flickr.presentation.FlickrListDetailsLayout
import com.creatare.cvsccapp.flickr.presentation.FlickrViewModel
import com.creatare.cvsccapp.ui.theme.AndroidTemplateProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTemplateProjectTheme {

                val viewModel: FlickrViewModel = hiltViewModel()

                val flickrState = viewModel.state.collectAsStateWithLifecycle().value
                val searchTags = viewModel.searchTags.collectAsStateWithLifecycle().value

                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                    FlickrListDetailsLayout(
                        flickrState,
                        searchTags,
                        viewModel,
                        LocalContext.current
                    )
                }
            }
        }
    }
}
