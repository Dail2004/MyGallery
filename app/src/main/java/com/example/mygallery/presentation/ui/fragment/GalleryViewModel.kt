package com.example.mygallery.presentation.ui.fragment

import com.example.mygallery.common.base.BaseFetchRequest
import com.example.mygallery.common.base.BaseViewModel
import com.example.mygallery.data.model.GalleryModel
import com.example.mygallery.data.repository.PhotosRepository
import com.example.mygallery.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: PhotosRepository
) : BaseViewModel(), BaseFetchRequest {

    override var per_page = 10

    private val _galleryState = MutableStateFlow<UIState<List<GalleryModel>>>(UIState.Loading())
    val galleryState: StateFlow<UIState<List<GalleryModel>>> = _galleryState.asStateFlow()

    init {
        fetchGallery(10)
    }

    override fun fetchGallery(per_page: Int) {
        repository.fetchPhotos(per_page).collectRequests(_galleryState) {
            it.map { data -> data }
        }
    }

}