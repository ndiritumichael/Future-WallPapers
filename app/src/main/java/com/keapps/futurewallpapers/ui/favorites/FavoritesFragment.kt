package com.keapps.futurewallpapers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.WallPaperApplication
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.databinding.FragmentFavoritesBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersFragmentDirections

class FavoritesFragment : Fragment(),WallPaperAdapter.OnClickPicListener {
    private val favoritesViewModel : FavoritesViewModel by viewModels {
        FavWallViewModelFactory((activity?.application as WallPaperApplication).repository)
    }
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var wallpaperAdapter : WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        gridLayoutManager = GridLayoutManager(context,2)
        wallpaperAdapter = WallPaperAdapter(emptyList<WallPaperModel>()  , this)
       binding.wallpaperRecycler.adapter = wallpaperAdapter
        binding.wallpaperRecycler.layoutManager = gridLayoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesViewModel.favWallPaper.observe(viewLifecycleOwner){ wallpapers ->
            wallpaperAdapter.updateData(wallpapers)
        }
    }

    override fun onItemClicked(wallpaper: WallPaperModel) {
        val action = WallpapersFragmentDirections
                .actionNavigationWallpapersToFullScreenImage(wallpaper.lowHd,wallpaper.Title)
         findNavController().navigate(action)
    }
}