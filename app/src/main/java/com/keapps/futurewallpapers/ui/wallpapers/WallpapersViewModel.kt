package com.keapps.futurewallpapers.ui.wallpapers

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.Query

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
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

    fun getfirebaseWallpapers(){
val wallpapaersfirebase = Firebase.firestore.collection("wallpapers")
        wallpapaersfirebase.orderBy("wallId",Query.Direction.ASCENDING)
            .addSnapshotListener { value, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    it.message?.let { it1 -> Log.d("viewmodel", it1) }
                    return@addSnapshotListener
                }

                value.let {
if (it!=null){
it.forEach {document ->

val game = document.toObject<WallPaperModel>()
    Log.d("viewmodel","$game")
   viewModelScope.launch {
       wallpapersRepo.insertWallpaper(game)
   }

}
}
                }
            }
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
