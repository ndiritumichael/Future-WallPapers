package com.keapps.futurewallpapers.ui.wallpapers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.Wallpapers

class WallpapersViewModel : ViewModel() {
    private val _wallpaperlist : MutableLiveData<List<WallPaperModel>> = MutableLiveData()
    init {
        _wallpaperlist.value = Wallpapers().list
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val wallpaperlist : LiveData<List<WallPaperModel>>
    get() = _wallpaperlist
}