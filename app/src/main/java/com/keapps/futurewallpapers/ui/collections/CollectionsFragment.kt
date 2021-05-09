package com.keapps.futurewallpapers.ui.collections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keapps.futurewallpapers.R.layout.fragment_collections
import com.keapps.futurewallpapers.adapter.CollectionsAdapter
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.data.relationships.CategoryinWall
import com.keapps.futurewallpapers.databinding.FragmentCollectionsBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsFragment : Fragment(fragment_collections),CollectionsAdapter.OnClickPicListener{

    private val collectionsViewModel: CollectionsViewModel by viewModels()
    private val binding:FragmentCollectionsBinding by viewBinding()
    private lateinit var adapter:CollectionsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CollectionsAdapter(this)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.collectionsrv.layoutManager = linearLayoutManager
        binding.collectionsrv.adapter = adapter

collectionsViewModel.allCategories.observe(viewLifecycleOwner){ categoryList ->
    val filteredList: MutableList<CategoryinWall> = categoryList as MutableList<CategoryinWall>
  val final = filteredList.filterIndexed { _, categoryinWall ->
      categoryinWall.wallpapers.isNotEmpty()
  }


    adapter.submitList(final)

}

    }



    override fun onItemClicked(categoryinWall: CategoryinWall) {
        val action = CollectionsFragmentDirections.actionNavigationCollectionsToSingleCollection(categoryinWall)
        findNavController().navigate(action)
    }

}