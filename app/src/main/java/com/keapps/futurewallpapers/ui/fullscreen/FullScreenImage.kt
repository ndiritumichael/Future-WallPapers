package com.keapps.futurewallpapers.ui.fullscreen

import android.app.WallpaperManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.navArgs
import coil.load
import com.keapps.futurewallpapers.databinding.ActivityFullScreenImageBinding
import java.io.IOException

class FullScreenImage : AppCompatActivity() {
    private lateinit var bitmap : Bitmap
    private  val args: FullScreenImageArgs by navArgs()
    private lateinit var binding: ActivityFullScreenImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = args.title


        setupUI()

    }

    private fun setupUI() {
        binding.fullscreenimage.load(args.imageLink)

        binding.applyWallpaper.setOnClickListener {
setWallpaper()
        }
    }

    private fun setWallpaper() {
        val wallPaperManager = WallpaperManager.getInstance(this)
        bitmap = binding.fullscreenimage.drawable.toBitmap()
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
            Toast.makeText(this,"Image has not yet loaded",Toast.LENGTH_SHORT).show()
        }
    }
}