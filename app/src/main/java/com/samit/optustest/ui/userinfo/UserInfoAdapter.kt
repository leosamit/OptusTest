package com.samit.optustest.ui.userinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samit.optustest.databinding.ItemUserInfoBinding
import io.reactivex.subjects.PublishSubject

class UserInfoAdapter : ListAdapter<UserInfoUI, UserInfoAdapter.UserInfoViewHolder>(
    UserInfoDiffCallback()
) {

    private val userClicksPublish = PublishSubject.create<UserInfoUI>()
    val userClicks = userClicksPublish

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        return UserInfoViewHolder(
            ItemUserInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), userClicksPublish
        )
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class UserInfoViewHolder(
        private val binding: ItemUserInfoBinding, private val itemClicks: PublishSubject<UserInfoUI>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(userInfo: UserInfoUI) {
            binding.userInfo = userInfo
            binding.root.setOnClickListener {
                itemClicks.onNext(userInfo)
            }
        }
    }
}

class UserInfoDiffCallback : DiffUtil.ItemCallback<UserInfoUI>() {
    override fun areItemsTheSame(oldItem: UserInfoUI, newItem: UserInfoUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserInfoUI, newItem: UserInfoUI): Boolean {
        return oldItem == newItem
    }
}