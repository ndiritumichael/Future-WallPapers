package com.keapps.futurewallpapers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.databinding.FragmentFavoritesBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.utils.Consts

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), WallPaperAdapter.OnClickPicListener {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var wallpaperAdapter: WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        gridLayoutManager = GridLayoutManager(context, 2)
        wallpaperAdapter = WallPaperAdapter(
            this
        ) { wallPaperModel: WallPaperModel, b: Boolean ->
            toggleFavorites(wallPaperModel, b)

        }
        binding.wallpaperRecycler.adapter = wallpaperAdapter
        binding.wallpaperRecycler.layoutManager = gridLayoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesViewModel.favWallPaper.observe(viewLifecycleOwner) { wallpapers ->
            if (wallpapers.isEmpty()) {
                binding.apply {
                    animationView.visibility = View.VISIBLE
                    noFavorites.visibility = View.VISIBLE
                }

            }
            wallpaperAdapter.submitList(wallpapers)
        }
    }

    override fun onItemClicked(wallpaper: WallPaperModel) {

        val action = FavoritesFragmentDirections.actionNavigationFavoritesToFullscreenFragment(
            wallpaper.wallId,
            Consts.favorites
        )
        findNavController().navigate(action)
    }

    private fun toggleFavorites(wallPaperModel: WallPaperModel, isTrue: Boolean) {
        wallPaperModel.favorites = isTrue
        favoritesViewModel.updateData(wallPaperModel)
    }
}