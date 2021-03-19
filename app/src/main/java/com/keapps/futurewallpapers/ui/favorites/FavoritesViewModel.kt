package com.keapps.futurewallpapers.ui.favorites

import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersViewModel
import java.lang.IllegalArgumentException

class FavoritesViewModel( wallpapersRepo: WallpapersRepo) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val favWallPaper : LiveData<List<WallPaperModel>> = wallpapersRepo.favWallPapers.asLiveData()

}

@Suppress("UNCHECKED_CAST")
class FavWallViewModelFactory(private val repository:WallpapersRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown Viewmodel Class")
    }

}