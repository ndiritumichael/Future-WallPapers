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
import com.keapps.futurewallpapers.ui.dialog.ItemListDialogFragmentDirections
import com.keapps.futurewallpapers.utils.Consts
import com.keapps.futurewallpapers.utils.Statuses
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

        fullScreenViewModel.status.observe(viewLifecycleOwner){ status ->
            Log.d("status","$status")
            when(status){
                Statuses.SUCCESS -> {
                    Toast.makeText(context, "Successfully Updated Wallpaper", Toast.LENGTH_SHORT).show()
                    binding.loadingBar.visibility = View.GONE
                }
                Statuses.ERROR -> {
                    binding.loadingBar.visibility = View.GONE
                    Toast.makeText(context, "Failed ,Please try again", Toast.LENGTH_SHORT).show()
                }
                Statuses.LOADING -> {
                    binding.loadingBar.visibility = View.VISIBLE
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()

                }
            }
        }

        fullScreenViewModel.getSTA().observe(viewLifecycleOwner){
            when(it){
                1->{
                    Toast.makeText(context, "Successfully Updated Wallpaper", Toast.LENGTH_SHORT).show()
                }
                3-> {
                    Toast.makeText(context, "Successfully Updated Wallpaper $it", Toast.LENGTH_SHORT).show()
                }
                else->{
                    Toast.makeText(context, "Successfully did something", Toast.LENGTH_SHORT).show()
                }
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
        binding.apply {
            infoText.setOnClickListener {

            }
            applyText.setOnClickListener {

            }

        }

        binding.applyWallpaper.setOnClickListener {
            bitmap = binding.fullscreenimage.drawable.toBitmap()
            setWallpaper()
            fullScreenViewModel.getSTA().observe(viewLifecycleOwner){
                Toast.makeText(context,"The number in question is $it",Toast.LENGTH_SHORT).show()
            }
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
            fullScreenViewModel.setValues(wallPaperManager,bitmap)
            val action = FullscreenFragmentDirections.actionFullscreenFragmentToItemListDialogFragment()
            findNavController().navigate(action)
         /*  val status = fullScreenViewModel.setWallPaper()
            if (!status){

            }else {

            }*/

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