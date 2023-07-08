package com.example.mygallery.presentation.ui.fragment

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mygallery.R
import com.example.mygallery.common.base.BaseFragment
import com.example.mygallery.databinding.FragmentGalleryBinding
import com.example.mygallery.presentation.state.UIState
import com.example.mygallery.presentation.ui.adapter.GalleryAdapter
import dagger.hilt.android.AndroidEntryPoint

class GalleryFragment :
    BaseFragment<GalleryViewModel, FragmentGalleryBinding>(R.layout.fragment_gallery) {
    override val binding by viewBinding(FragmentGalleryBinding::bind)
    override val viewModel: GalleryViewModel by viewModels()
    private val galleryAdapter = GalleryAdapter()

    override fun initialize() {
        binding.rvGallery.apply {
            adapter = galleryAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.fetchGallery()
    }

    override fun setupRequests() {
        viewModel.galleryState.collectUIState {
            when (it) {
                is UIState.Error -> {}
                is UIState.Loading -> {}
                is UIState.Success -> {
                    galleryAdapter.submitList(it.data)
                }
            }
        }
    }
}