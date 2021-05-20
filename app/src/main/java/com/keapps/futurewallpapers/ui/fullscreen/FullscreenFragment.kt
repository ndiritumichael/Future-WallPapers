package com.keapps.futurewallpapers.ui.fullscreen

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.viewbinding.library.fragment.viewBinding
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import coil.load
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.databinding.FragmentFullscreenBinding
import com.keapps.futurewallpapers.model.WallPaperModel

import com.keapps.futurewallpapers.utils.Consts
import com.keapps.futurewallpapers.utils.Statuses
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.properties.Delegates

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
class FullscreenFragment : Fragment(R.layout.fragment_fullscreen) {
    private val binding: FragmentFullscreenBinding by viewBinding()
    private val fullScreenViewModel: FullscreenViewModel by viewModels()
    private val args: FullscreenFragmentArgs by navArgs()
    private lateinit var bitmap: Bitmap
    private lateinit var action: NavDirections


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setHasOptionsMenu(true)
        fullScreenViewModel.getImage(args.wallpaperId)




        fullScreenViewModel.getCat(args.wallpaperId)
        binding.backButton.setOnClickListener {

                findNavController().navigateUp()


        }

        fullScreenViewModel.fullPaper.observe(viewLifecycleOwner) { wallPaper ->
            setupUI(wallPaper)
            binding.wallTitle.text = wallPaper.title
            (activity as AppCompatActivity).supportActionBar!!.title = wallPaper.title

        }
        fullScreenViewModel.categories.observe(viewLifecycleOwner) {
            it?.let {


                Log.d("fullScreen", "${it.categories}")
            }
        }

        fullScreenViewModel.categories.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("this", "The Categories are $it")
            }
        }


    }




    private fun setupUI(wallPaperModel: WallPaperModel) {
        if (wallPaperModel.favorites) {
            showFav()
        } else {
            showNotFav()
        }

        binding.fullscreenimage.load(wallPaperModel.fullHd)
        binding.apply {
            infoText.setOnClickListener {

            }
            applyText.setOnClickListener {

            }

        }

        binding.applyWallpaper.setOnClickListener {
            bitmap = binding.fullscreenimage.drawable.toBitmap()
            // setWallpaper()
            setClickables()
            binding.list.visibility = View.VISIBLE

        }
        binding.favoriteTicked.setOnClickListener {

            toggleFavorites(wallPaperModel, false)
            showNotFav()
        }
        binding.favoriteUnticked.setOnClickListener {
            toggleFavorites(wallPaperModel, true)

            showFav()

        }
    }

    private fun setWallpaper(type: Int) {
        val wallPaperManager = WallpaperManager.getInstance(context)

        if (binding.fullscreenimage.drawable != null) {
            try {

                wallPaperManager.let {
                    it?.setBitmap(bitmap) ?: Log.d("null", "manager empty")
                }


            } catch (e: IOException) {
                e.printStackTrace()


            }


        } else {
            Toast.makeText(context, "Image has not yet loaded", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFav() {
        binding.favoriteTicked.visibility = View.VISIBLE
        binding.favoriteUnticked.visibility = View.GONE
    }

    private fun showNotFav() {
        binding.favoriteUnticked.visibility = View.VISIBLE
        binding.favoriteTicked.visibility = View.GONE
    }

    private fun toggleFavorites(wallPaperModel: WallPaperModel, isTrue: Boolean) {
        wallPaperModel.favorites = isTrue
        fullScreenViewModel.updateData(wallPaperModel)
    }

    private fun setClickables() {
        binding.both.setOnClickListener {
            binding.cardProgress.visibility = View.VISIBLE
           hideViews()

         setWAllpaper(3)



           binding.list.visibility = View.GONE
            showViews()
        }
        binding.homescreen.setOnClickListener {
            //binding.cardProgress.visibility = View.VISIBLE
            hideViews()

                setWAllpaper(1)
            showViews()
            binding.list.visibility = View.GONE
        }
        binding.lockscreen.setOnClickListener {
            binding.cardProgress.visibility = View.VISIBLE
            hideViews()

                setWAllpaper(2)

            showViews()

            binding.list.visibility = View.GONE
        }
        binding.cancelBt.setOnClickListener {
            binding.list.visibility = View.GONE
        }
    }

    private fun hideViews() {
        binding.cardProgress.visibility = View.VISIBLE
        binding.apply {
            cancelBt.visibility = View.GONE
            both.visibility = View.GONE
            homescreen.visibility = View.GONE
            lockscreen.visibility = View.GONE
        }
    }
    private fun showViews() {

        binding.apply {
            cancelBt.visibility = View.VISIBLE
            both.visibility = View.VISIBLE
            homescreen.visibility = View.VISIBLE
            lockscreen.visibility = View.VISIBLE
            list.visibility = View.GONE
            binding.cardProgress.visibility = View.GONE

        }
    }


    fun setWAllpaper(options: Int) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        try {
            when (options) {
                1 -> {
                    wallpaperManager.run {
                        this.setBitmap(bitmap)
                        Toast.makeText(
                            context,
                            "Succesfully Updated Wallpaper",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                2 -> {

                    wallpaperManager.run {
                        setBitmap(bitmap)
                        // setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                        toasting()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                        } else {
                            Toast.makeText(
                                context,
                                "Cant set Lockscreen Wallpaper in devices with Android 7.0",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                3 -> {
                    wallpaperManager.run {
                        setBitmap(bitmap)
                        Toast.makeText(
                            context,
                            "Succesfully Updated Wallpaper",
                            Toast.LENGTH_SHORT
                        ).show()
                      //  toasting()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                        } else {
                            Toast.makeText(
                                context,
                                "Cant set Lockscreen Wallpaper in devices with Android 7.0",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun toasting() {
        Toast.makeText(
            context,
            "Not all Phones allow 3rd party Apps to Change Lockscreen  ",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDetach() {
        super.onDetach()

    }

}