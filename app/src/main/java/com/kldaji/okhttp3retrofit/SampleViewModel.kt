package com.kldaji.okhttp3retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SampleViewModel: ViewModel() {
    private val _images = MutableLiveData<MutableList<Image>>(mutableListOf())
    val images: LiveData<MutableList<Image>> = _images

    fun addImage(image: Image){
        val temp = _images.value?.toMutableList() ?: return

        temp.add(image)
        _images.value = temp
    }

    fun clearImages() {
        _images.value = mutableListOf()
    }
}
