package com.keapps.futurewallpapers.ui.fullscreen

import android.util.Log
import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo

import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FullscreenViewModel(val repo : WallpapersRepo): ViewModel() {

    val fullPaper : MutableLiveData<WallPaperModel> = MutableLiveData()
    fun getImage(id:Int) = viewModelScope.launch {
      fullPaper.value = repo.fullWallpaper(id)
        Log.d("FullViewModel","${fullPaper.value}")
        Log.d("FullViewModel","$id")

    }

    fun updateData(wallPaperModel: WallPaperModel) = viewModelScope.launch {
       repo.updateData(wallPaperModel)


    }
}


@Suppress("UNCHECKED_CAST")
class FullWallViewModelFactory(private val repository: WallpapersRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullscreenViewModel::class.java)) {
            return FullscreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }

}