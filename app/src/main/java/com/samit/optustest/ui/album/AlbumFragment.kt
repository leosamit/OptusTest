package com.samit.optustest.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.samit.optustest.R
import com.samit.optustest.data.Result
import com.samit.optustest.databinding.FragmentAlbumBinding
import com.samit.optustest.di.injectViewModel
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class AlbumFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AlbumViewModel
    private lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.isLoading = true
        viewModel = injectViewModel(viewModelFactory)
        binding.executePendingBindings()
        binding.srlFacts.setOnRefreshListener {
            //refresh()
        }
        subscribeUI()
    }

    private fun subscribeUI() {

        viewModel.albumList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.album = result.data
                    binding.isLoading = false
                    Timber.d("Samit ALbum ${result.data}")
                }
                Result.Status.ERROR -> {
                    binding.isLoading = false
                }

            }

        }
    }
}