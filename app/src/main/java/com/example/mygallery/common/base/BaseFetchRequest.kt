package com.example.mygallery.common.base

interface BaseFetchRequest {
    var per_page: Int
    fun fetchGallery(per_page: Int)
}