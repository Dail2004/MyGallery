package com.example.mygallery.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygallery.common.base.BaseComparator
import com.example.mygallery.data.model.GalleryModel
import com.example.mygallery.databinding.ItemGalleryBinding

class GalleryAdapter(
    val onItemClick: (image: String) -> Unit,
) :
    ListAdapter<GalleryModel, GalleryAdapter.GalleryViewHolder>(BaseComparator()) {
    inner class GalleryViewHolder(
        private val binding: ItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.apply {
                    onItemClick(urls.full)
                }
            }
        }

        fun onBind(model: GalleryModel) {
            Glide.with(binding.photo).load(model.urls.full).into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }
}