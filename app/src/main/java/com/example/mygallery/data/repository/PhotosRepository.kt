package com.example.mygallery.data.repository

import com.example.mygallery.common.base.BaseRepository
import com.example.mygallery.data.local.apiservice.PhotoApiService
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val service: PhotoApiService
) : BaseRepository() {

    fun fetchPhotos() = doRequest {
        service.fetchPhotos("WsjQGwxhGZnM4FCUvwUyQJeIHI1f5Wc-7DhKaild_r0").map { it }
    }
}