package com.samit.optustest.ui.album

import androidx.lifecycle.ViewModel
import com.samit.optustest.data.repo.AlbumRepository
import javax.inject.Inject

class AlbumViewModel @Inject constructor(private val repository: AlbumRepository) :
    ViewModel() {

    val albumList = repository.albumList

    init {
        repository.getAlbum()
    }

    fun refreshAlbum() {
        repository.getAlbum()
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelAllRequests()
    }
}