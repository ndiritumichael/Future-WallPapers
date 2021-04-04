package com.keapps.futurewallpapers.ui.favorites

import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject
@HiltViewModel
class FavoritesViewModel @Inject constructor(val wallpapersRepo: WallpapersRepo) : ViewModel() {

    fun updateData(wallPaperModel: WallPaperModel) = viewModelScope.launch {
        wallpapersRepo.updateData(wallPaperModel)


    }

    val favWallPaper: LiveData<List<WallPaperModel>> = wallpapersRepo.favWallPapers.asLiveData()

}

/*
@Suppress("UNCHECKED_CAST")
class FavWallViewModelFactory @Inject constructor( val repository: WallpapersRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }

}*/
