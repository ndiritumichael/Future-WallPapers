package com.keapps.futurewallpapers.ui.collections

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.databinding.FragmentWallpapersBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersViewModel
import com.keapps.futurewallpapers.utils.Consts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleCollection:Fragment(R.layout.fragment_wallpapers),WallPaperAdapter.OnClickPicListener {
    private val binding :FragmentWallpapersBinding by viewBinding()
    private val wallpapersViewModel:WallpapersViewModel by viewModels()

    private val args:SingleCollectionArgs by navArgs()
    private lateinit var wallpaperAdapter : WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.title = args.singleCollection?.category?.categories
        setUpUI()

    }

    private fun setUpUI() {
        gridLayoutManager = GridLayoutManager(context,2)
        wallpaperAdapter = WallPaperAdapter(
            this){ wallPaperModel: WallPaperModel, favorite: Boolean ->
            toggleFavorites(wallPaperModel,favorite)
        }
        binding.wallpaperRecycler.adapter = wallpaperAdapter
        binding.wallpaperRecycler.layoutManager = gridLayoutManager
        wallpaperAdapter.submitList(args.singleCollection?.wallpapers)

    }

    override fun onItemClicked(wallpaper: WallPaperModel) {
        val action = SingleCollectionDirections.actionSingleCollectionToFullscreenFragment(wallpaper.wallId,Consts.singlecollection)
        findNavController().navigate(action)

    }

    private fun toggleFavorites(wallPaperModel: WallPaperModel,isTrue:Boolean){
        wallPaperModel.favorites = isTrue
        wallpapersViewModel.updateData(wallPaperModel)
    }


}