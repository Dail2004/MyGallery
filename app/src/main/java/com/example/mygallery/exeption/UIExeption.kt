package com.example.mygallery.exeption

import androidx.recyclerview.widget.RecyclerView
import com.example.mygallery.common.base.BaseFetchRequest

fun RecyclerView.scrollListenerUploadNextPage(viewModel: BaseFetchRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.per_page++
                viewModel.fetchGallery(viewModel.per_page)
            }
        }
    })
}