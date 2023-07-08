package com.example.mygallery.data.local.apiservice

import com.example.mygallery.data.local.model.GalleryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {

    @GET("/photos")
    suspend fun fetchPhotos(
        @Query("client_id") client_id: String,
    ): List<GalleryModel>

}