package com.keapps.futurewallpapers.ui.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.keapps.futurewallpapers.R.layout.fragment_collections
import com.keapps.futurewallpapers.databinding.FragmentCollectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionsFragment : Fragment(fragment_collections) {

    val collectionsViewModel: CollectionsViewModel by viewModels()
    private val binding:FragmentCollectionsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
collectionsViewModel.getAllCategories().observe(viewLifecycleOwner){

}

    }

}