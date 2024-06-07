package com.creatare.cvsccapp.flickr.presentation


import Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creatare.cvsccapp.flickr.domain.use_case.SearchPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(
    private val searchPhotos: SearchPhotos
) : ViewModel() {

    private val _state = MutableStateFlow(FlickrState())
    val state: StateFlow<FlickrState> = _state.asStateFlow()

    private val _searchTags = MutableStateFlow("")
    val searchTags = _searchTags.asStateFlow()

    private var searchJob: Job? = null

    init {
        searchFlickrPhotos("cats")
    }

    fun searchFlickrPhotos(tags: String) {
        _searchTags.value = tags
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            searchPhotos(tags)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                flickrData = result.data ?: null,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                flickrData = null,
                                isLoading = false
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                flickrData = null,
                                isLoading = true
                            )
                        }

                        else -> {}
                    }
                }.launchIn(this)
        }
    }

}