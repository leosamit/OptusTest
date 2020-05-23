package com.samit.optustest.ui.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.samit.optustest.MainActivity
import com.samit.optustest.R
import com.samit.optustest.data.Result
import com.samit.optustest.databinding.FragmentUserInfoBinding
import com.samit.optustest.di.injectViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user_info.*
import javax.inject.Inject

class UserInfoFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UserInfoViewModel
    private lateinit var binding: FragmentUserInfoBinding

    private val actionBar: ActionBar?
        get() = (activity as? MainActivity)?.supportActionBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        actionBar?.apply {
            title = getString(R.string.user_info)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
            setHomeButtonEnabled(false)
        }
        binding.isLoading = true
        viewModel = injectViewModel(viewModelFactory)
        binding.executePendingBindings()
        binding.srlFacts.setOnRefreshListener {
            //refresh()
        }
        subscribeUI()
    }

    private fun subscribeUI() {

        viewModel.userInfoList.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.userInfos = result.data
                    binding.isLoading = false
                }
                Result.Status.ERROR -> {
                    binding.isLoading = false
                }
            }
        }

        (rv_userInfo.adapter as? UserInfoAdapter)?.run {
            userClicks.subscribe {
                findNavController().navigate(UserInfoFragmentDirections.navigateToAlbum(it.id.toString()))

            }
        }
    }
}