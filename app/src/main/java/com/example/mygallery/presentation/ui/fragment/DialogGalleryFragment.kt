package com.example.mygallery.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.mygallery.R
import com.example.mygallery.databinding.FragmentGalleryDialogBinding

class DialogGalleryFragment : Fragment(R.layout.fragment_gallery_dialog) {
    private val binding by viewBinding(FragmentGalleryDialogBinding::bind)
    private val args: DialogGalleryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        Glide.with(binding.image).load(args.image).into(binding.image)
    }
}