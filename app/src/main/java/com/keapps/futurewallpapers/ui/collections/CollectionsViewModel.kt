package com.keapps.futurewallpapers.ui.collections

import androidx.lifecycle.*
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor( val wallpapersRepo: WallpapersRepo) : ViewModel() {
val allCategories
get() = wallpapersRepo.categories.asLiveData()



}