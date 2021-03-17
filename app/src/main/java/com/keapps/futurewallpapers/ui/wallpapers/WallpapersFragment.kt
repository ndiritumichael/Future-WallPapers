package com.keapps.futurewallpapers.ui.wallpapers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.databinding.FragmentWallpapersBinding
import com.keapps.futurewallpapers.model.WallPaperModel

class WallpapersFragment : Fragment() {


    private lateinit var wallpapersViewModel: WallpapersViewModel
    private lateinit var wallpapersBinding: FragmentWallpapersBinding
    private lateinit var wallpaperAdapter : WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        wallpapersViewModel =
                ViewModelProvider(this).get(WallpapersViewModel::class.java)
        wallpapersBinding = FragmentWallpapersBinding.inflate(inflater,container,false)

       /* val textView: TextView = root.findViewById(R.id.text_home)
        wallpapersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        gridLayoutManager = GridLayoutManager(context,2)
        wallpaperAdapter = WallPaperAdapter(emptyList<WallPaperModel>() )
        wallpapersBinding.wallpaperRecycler.adapter = wallpaperAdapter
        wallpapersBinding.wallpaperRecycler.layoutManager = gridLayoutManager

        return wallpapersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wallpapersViewModel.wallpaperlist.observe(viewLifecycleOwner){ list->
            wallpaperAdapter.updateData(list)
        }
    }
}