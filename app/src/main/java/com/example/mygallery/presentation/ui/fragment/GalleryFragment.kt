package com.example.mygallery.presentation.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mygallery.R
import com.example.mygallery.common.base.BaseFragment
import com.example.mygallery.data.model.GalleryModel
import com.example.mygallery.databinding.FragmentGalleryBinding
import com.example.mygallery.exeption.scrollListenerUploadNextPage
import com.example.mygallery.presentation.state.UIState
import com.example.mygallery.presentation.ui.activity.MainActivity
import com.example.mygallery.presentation.ui.adapter.GalleryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment :
    BaseFragment<GalleryViewModel, FragmentGalleryBinding>(R.layout.fragment_gallery) {
    override val binding by viewBinding(FragmentGalleryBinding::bind)
    override val viewModel: GalleryViewModel by viewModels()
    private val galleryAdapter = GalleryAdapter(this::onItemClick)

    private fun onItemClick(image: String) {
        findNavController().navigate(
            GalleryFragmentDirections.actionGalleryFragmentToDialogGalleryFragment2(
                image
            )
        )
    }

    override fun initialize() {
        binding.rvGallery.adapter = galleryAdapter
    }

    override fun setupListeners() {
        onScrollListener()
        bottomNavigationItemReselectListener()
    }

    private fun onScrollListener() {
        binding.rvGallery.scrollListenerUploadNextPage(viewModel)
    }

    override fun setupRequests() {
        viewModel.galleryState.collectUIState {
            when (it) {
                is UIState.Loading -> {
                    binding.refresh.isRefreshing = true
                }

                is UIState.Error -> {
                    Log.e("anime", it.error)
                }

                is UIState.Success -> {
                    binding.refresh.isRefreshing = false
                    Log.d("TAG", "setupRequests: ${it.data}")
                    val dataList = ArrayList<GalleryModel>(galleryAdapter.currentList)
                    it.data.let { data -> dataList.addAll(data) }
                    galleryAdapter.submitList(dataList)
                }
            }
        }
    }

    private fun bottomNavigationItemReselectListener() {
        (requireActivity() as MainActivity).setOnBottomNavigationItemReselectListener {
            binding.rvGallery.smoothScrollToPosition(0)
        }
    }
}