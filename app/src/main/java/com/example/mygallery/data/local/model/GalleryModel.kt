package com.example.mygallery.data.local.model

import com.example.mygallery.common.base.IBaseDiffModel

data class GalleryModel(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<CurrentUserCollection>,
    val description: String,
    val height: Int,
    override val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
) : IBaseDiffModel