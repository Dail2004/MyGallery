package com.example.mygallery.data.repository

import com.example.mygallery.common.base.BaseRepository
import com.example.mygallery.data.local.apiservice.PhotoApiService
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val service: PhotoApiService
) : BaseRepository() {

    fun fetchPhotos(per_page: Int) = doRequest {

        service.fetchPhotos(per_page).map { it }
    }
}