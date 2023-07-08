package com.example.mygallery.common.base

interface IBaseDiffModel {
    val id: String
    override fun equals(other: Any?): Boolean
}