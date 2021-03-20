package com.keapps.futurewallpapers.ui.fullscreen

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.navArgs
import coil.load
import com.keapps.futurewallpapers.WallPaperApplication
import com.keapps.futurewallpapers.databinding.ActivityFullScreenImageBinding
import java.io.IOException

class FullScreenImage : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private val args: FullScreenImageArgs by navArgs()
    private lateinit var binding: ActivityFullScreenImageBinding
    private val fullScreenViewModel : FullscreenViewModel by viewModels {
        FullWallViewModelFactory((application as WallPaperApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreenViewModel.getImage(args.imageId)
        supportActionBar?.setHomeButtonEnabled(true)

        fullScreenViewModel.fullPaper.observe(this){wallPaper ->
            setupUI(wallPaper.lowHd)
           supportActionBar?.title = wallPaper.Title

        }

    }

    private fun setupUI(image: String) {

        binding.fullscreenimage.load(image)

        binding.applyWallpaper.setOnClickListener {
            bitmap = binding.fullscreenimage.drawable.toBitmap()
            setWallpaper()
        }
    }

    private fun setWallpaper() {
        val wallPaperManager = WallpaperManager.getInstance(this)

        if (binding.fullscreenimage.drawable != null) {
            try {

                wallPaperManager.setBitmap(bitmap)


                Toast.makeText(this, "SuccessFully Updated WallPaper", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Toast.makeText(this, "Something went wrong ,Please try again", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

        } else {
            Toast.makeText(this, "Image has not yet loaded", Toast.LENGTH_SHORT).show()
        }
    }
}