package com.samit.optustest.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.samit.optustest.R
import com.samit.optustest.databinding.FragmentPhotoBinding
import dagger.android.support.DaggerFragment

class PhotoFragment : DaggerFragment() {

    private lateinit var binding: FragmentPhotoBinding
    private val args by navArgs<PhotoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.executePendingBindings()
        binding.album = args.photo
    }
}