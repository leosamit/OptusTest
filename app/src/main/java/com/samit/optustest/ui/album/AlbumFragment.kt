package com.samit.optustest.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samit.optustest.MainActivity
import com.samit.optustest.R
import com.samit.optustest.data.Result
import com.samit.optustest.databinding.FragmentAlbumBinding
import com.samit.optustest.di.injectViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_album.*
import javax.inject.Inject

class AlbumFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AlbumViewModel
    private lateinit var binding: FragmentAlbumBinding
    private val args by navArgs<AlbumFragmentArgs>()

    private val actionBar: ActionBar?
        get() = (activity as? MainActivity)?.supportActionBar

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
        actionBar?.apply {
            title = getString(R.string.album)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeButtonEnabled(true)
        }
        binding.isLoading = true
        viewModel = injectViewModel(viewModelFactory)
        binding.executePendingBindings()
        binding.srlFacts.setOnRefreshListener {
            viewModel.refreshAlbum()
        }
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.albumList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.LOADING -> binding.isLoading = true
                Result.Status.SUCCESS -> {
                    binding.album = result.data?.filter { it.albumID.toString() == args.id }
                    binding.isLoading = false
                }
                Result.Status.ERROR -> binding.isLoading = false
            }
        }

        (rv_albums.adapter as? AlbumAdapter)?.run {
            photoClicks.subscribe {
                findNavController().navigate(AlbumFragmentDirections.navigatetoPhoto(it))
            }
        }
    }
}