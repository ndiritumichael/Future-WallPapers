package com.keapps.futurewallpapers.ui.fullscreen

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.navArgs
import coil.load
import com.keapps.futurewallpapers.WallPaperApplication
import com.keapps.futurewallpapers.databinding.ActivityFullScreenImageBinding
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
@AndroidEntryPoint
class FullScreenImage : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private val args: FullScreenImageArgs by navArgs()
    private lateinit var binding: ActivityFullScreenImageBinding
    private val fullScreenViewModel : FullscreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var category = emptyList<Categories>()
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreenViewModel.getImage(args.imageId)
        supportActionBar?.setHomeButtonEnabled(true)
        fullScreenViewModel.getCat(args.imageId)

        fullScreenViewModel.fullPaper.observe(this){wallPaper ->
            setupUI(wallPaper)
           supportActionBar?.title = wallPaper.Title

        }
        fullScreenViewModel.categories.observe(this){
            it?.let {
                category = it.categories

               Log.d("fullScreen","$category")
            }
        }

        fullScreenViewModel.categories.observe(this){
            it?.let {
                Log.d("this","The Categories are $it")
            }
        }

    }

    private fun setupUI(wallPaperModel: WallPaperModel) {
        if (wallPaperModel.favorites){
            showFav()
        }else{
            showNotFav()
        }

        binding.fullscreenimage.load(wallPaperModel.lowHd)

        binding.applyWallpaper.setOnClickListener {
            bitmap = binding.fullscreenimage.drawable.toBitmap()
            setWallpaper()
        }
        binding.favoriteTicked.setOnClickListener {

            toggleFavorites(wallPaperModel,false)
            showNotFav()
        }
        binding.favoriteUnticked.setOnClickListener {
            toggleFavorites(wallPaperModel,true)

            showFav()

        }
    }

    private fun setWallpaper() {
        val wallPaperManager = WallpaperManager.getInstance(this)

        if (binding.fullscreenimage.drawable != null) {
            try {

                wallPaperManager.setBitmap(bitmap)


                Toast.makeText(this, "SuccessFully Updated WallPaper", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Toast.makeText(this, "${e.message},Please try again", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

        } else {
            Toast.makeText(this, "Image has not yet loaded", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showFav(){
        binding.favoriteTicked.visibility = View.VISIBLE
        binding.favoriteUnticked.visibility = View.GONE
    }
    private fun showNotFav(){
        binding.favoriteUnticked.visibility= View.VISIBLE
        binding.favoriteTicked.visibility = View.GONE
    }

    private fun toggleFavorites(wallPaperModel: WallPaperModel, isTrue:Boolean){
        wallPaperModel.favorites = isTrue
        fullScreenViewModel.updateData(wallPaperModel)
    }
}