package com.keapps.futurewallpapers.ui.collections

import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.repository.WallpapersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollectionsViewModel(val wallpapersRepo: WallpapersRepo) : ViewModel() {
    private lateinit var categories: LiveData<List<String>>

    init {
        viewModelScope.launch {
            categories = wallpapersRepo.categories.asLiveData()
        }
    }

}