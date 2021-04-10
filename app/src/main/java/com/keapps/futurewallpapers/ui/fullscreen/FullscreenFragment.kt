package com.keapps.futurewallpapers.ui.fullscreen

import android.app.WallpaperManager
import android.graphics.Bitmap
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
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import kotlin.properties.Delegates

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
class FullscreenFragment : Fragment(R.layout.fragment_fullscreen) {
    private val binding : FragmentFullscreenBinding by viewBinding()
    private val fullScreenViewModel : FullscreenViewModel by viewModels()
    private val args: FullscreenFragmentArgs by navArgs()
    private lateinit var bitmap: Bitmap
    private lateinit var action :NavDirections


    @Suppress("InlinedApi")
    private fun showfull(){
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        val flags =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        activity?.window?.decorView?.systemUiVisibility = flags
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //setHasOptionsMenu(true)
        fullScreenViewModel.getImage(args.wallpaperId)




        fullScreenViewModel.getCat(args.wallpaperId)
        binding.backButton.setOnClickListener {
            when(args.source){
                Consts.favorites -> action = FullscreenFragmentDirections.actionFullscreenFragmentToNavigationFavorites()

                Consts.allwallpapers-> action = FullscreenFragmentDirections.actionFullscreenFragmentToNavigationWallpapers()

            }

            findNavController().navigate(action)
 }

        fullScreenViewModel.fullPaper.observe(viewLifecycleOwner){wallPaper ->
            setupUI(wallPaper)
            binding.wallTitle.text= wallPaper.Title
           (activity as AppCompatActivity).supportActionBar!!.title = wallPaper.Title

        }
        fullScreenViewModel.categories.observe(viewLifecycleOwner){
            it?.let {


                Log.d("fullScreen","${it.categories}")
            }
        }

        fullScreenViewModel.categories.observe(viewLifecycleOwner){
            it?.let {
                Log.d("this","The Categories are $it")
            }
        }








    }

    override fun onResume() {
        super.onResume()
      //  activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

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
        val wallPaperManager = WallpaperManager.getInstance(context)

        if (binding.fullscreenimage.drawable != null) {
            try {

                wallPaperManager.setBitmap(bitmap)


                Toast.makeText(context, "SuccessFully Updated WallPaper", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                Toast.makeText(context, "${e.message},Please try again", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

        } else {
            Toast.makeText(context, "Image has not yet loaded", Toast.LENGTH_SHORT).show()
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

    override fun onDetach() {
        super.onDetach()

    }

}