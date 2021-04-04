package com.keapps.futurewallpapers.ui.wallpapers

import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(private val wallpapersRepo: WallpapersRepo) : ViewModel() {

    val wallpaperlist  :LiveData<List<WallPaperModel>> =wallpapersRepo.allWallappers.asLiveData()

    fun insert(wallpaper : WallPaperModel) = viewModelScope.launch {
        wallpapersRepo.insertWallpaper(wallpaper)
    }

    fun updateData(wallPaperModel: WallPaperModel)= viewModelScope.launch{
        wallpapersRepo.updateData(wallPaperModel)

    }


}

/*
class WallViewModelFactory @Inject constructor(private val repository:WallpapersRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WallpapersViewModel::class.java)){
            return WallpapersViewModel(repository)as T
        }
        throw IllegalArgumentException("Unkown Viewmodel Class")
    }

}*/
