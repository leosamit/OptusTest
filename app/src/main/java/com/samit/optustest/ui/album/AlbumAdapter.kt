package com.samit.optustest.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samit.optustest.databinding.ItemAlbumBinding
import io.reactivex.subjects.PublishSubject

class AlbumAdapter : ListAdapter<AlbumUI, AlbumAdapter.AlbumViewHolder>(
    AlbumDiffCallback()
) {
    private val photoClickPublish = PublishSubject.create<AlbumUI>()
    val photoClicks = photoClickPublish

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        return AlbumViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), photoClickPublish
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }


    class AlbumViewHolder(
        private val binding: ItemAlbumBinding, private val itemClicks: PublishSubject<AlbumUI>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(albumUI: AlbumUI) {
            binding.album = albumUI
            binding.root.setOnClickListener {
                itemClicks.onNext(albumUI)
            }
        }
    }
}

class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumUI>() {
    override fun areItemsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumUI, newItem: AlbumUI): Boolean {
        return oldItem == newItem
    }
}