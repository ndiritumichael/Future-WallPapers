package com.keapps.futurewallpapers.ui.fullscreen

import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.keapps.futurewallpapers.data.relationships.WallpaperinCategories
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.repository.WallpapersRepo
import com.keapps.futurewallpapers.utils.Statuses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class FullscreenViewModel @Inject constructor (val repo : WallpapersRepo):ViewModel(){
   var wallpaperManager: WallpaperManager? = null
    lateinit var bitmap: Bitmap
    var status = MutableLiveData<Statuses>()
    var STA : MutableLiveData<Int> = liveData {
        emit(5)
    } as MutableLiveData<Int>


    fun changeValue(id: Int) = setSTA(id)

    private fun setSTA(i : Int){
        Log.d("viewmodel","values set $i")
        STA.value = i
    }

    fun getSTA():LiveData<Int>{
        return STA
    }

        fun setValues(wallpaperManager1: WallpaperManager,bitmap1: Bitmap){

        wallpaperManager = wallpaperManager1
        bitmap = bitmap1



    }



  private val _categories : MutableLiveData<WallpaperinCategories> = MutableLiveData()

            fun getCat(id: Int)= viewModelScope.launch {
             _categories.value=   repo.getcatinWall(id)
            }
    val categories
        get() = _categories


    val fullPaper : MutableLiveData<WallPaperModel> = MutableLiveData()
    fun getImage(id:Int) = viewModelScope.launch {
      fullPaper.value = repo.fullWallpaper(id)
        Log.d("FullViewModel","${fullPaper.value}")
        Log.d("FullViewModel","$id")


    }





    fun updateData(wallPaperModel: WallPaperModel) = viewModelScope.launch {
       repo.updateData(wallPaperModel)


    }

    fun setWallPaper(){

        status.postValue(Statuses.LOADING)
        Log.d("viewmodel","setting wallpaper ${status.value}")
        try {

            wallpaperManager.let {
                it?.setBitmap(bitmap) ?: Log.d("null","manager empty")
            }
            status.postValue(Statuses.SUCCESS)

        } catch (e: IOException) {
            e.printStackTrace()
            status.postValue(Statuses.ERROR)
            Log.d("printin","${status.value}")
        }
    }

    fun printValues(){
        Log.d("printin","${status.value}")
    }
}
/*


@Suppress("UNCHECKED_CAST")
class FullWallViewModelFactory(private val repository: WallpapersRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullscreenViewModel::class.java)) {
            return FullscreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }

}*/
