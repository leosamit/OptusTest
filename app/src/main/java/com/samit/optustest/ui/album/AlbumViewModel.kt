package com.samit.optustest.ui.album

import androidx.lifecycle.ViewModel
import com.samit.optustest.data.repo.AlbumRepository
import javax.inject.Inject

class AlbumViewModel @Inject constructor(private val repository: AlbumRepository) :
    ViewModel() {

    init {
        repository.getAlbum()
    }

    val albumList = repository.albumList

    override fun onCleared() {
        super.onCleared()
        repository.cancelAllRequests()
    }
}