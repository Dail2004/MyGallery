package com.example.mygallery.data.local.apiservice

import com.example.mygallery.data.model.GalleryModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {

    @GET("/photos/?client_id=WsjQGwxhGZnM4FCUvwUyQJeIHI1f5Wc-7DhKaild_r0")
    suspend fun fetchPhotos(
        @Query("per_page") per_page: Int
    ): List<GalleryModel>

}