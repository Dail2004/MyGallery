package com.example.mygallery.presentation.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mygallery.R
import com.example.mygallery.common.base.BaseFragment
import com.example.mygallery.databinding.FragmentGalleryBinding
import com.example.mygallery.presentation.state.UIState
import com.example.mygallery.presentation.ui.adapter.GalleryAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GalleryFragment :
    BaseFragment<GalleryViewModel, FragmentGalleryBinding>(R.layout.fragment_gallery) {
    override val binding by viewBinding(FragmentGalleryBinding::bind)
    override val viewModel: GalleryViewModel by viewModels()
    private val galleryAdapter = GalleryAdapter(this::onItemClick)
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private fun onItemClick(image: String) {
        findNavController().navigate(
            GalleryFragmentDirections.actionGalleryFragmentToDialogGalleryFragment2(
                image
            )
        )
    }

    override fun initialize() {
        binding.rvGallery.apply {
            adapter = galleryAdapter
        }

        swipeRefreshLayout = binding.refresh
    }

    override fun setupRequests() {
        viewModel.galleryState.collectUIState {
            when (it) {
                is UIState.Error -> {
                    Log.d("TAG", "setupRequests: ${it.error}")
                }

                is UIState.Loading -> {
                }

                is UIState.Success -> {
                    Log.d("TAG", "setupRequests: ${it.data}")
                    binding.refresh.setOnRefreshListener {
                        galleryAdapter.submitList(it.data)
                        swipeRefreshLayout?.isRefreshing = false
                    }
                }
            }
        }
    }

}