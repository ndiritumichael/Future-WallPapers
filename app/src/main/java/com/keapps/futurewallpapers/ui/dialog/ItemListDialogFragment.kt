package com.keapps.futurewallpapers.ui.dialog

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.databinding.FragmentItemListDialogBinding
import com.keapps.futurewallpapers.ui.fullscreen.FullscreenViewModel
import dagger.hilt.android.AndroidEntryPoint




/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
@AndroidEntryPoint
class ItemListDialogFragment : BottomSheetDialogFragment() {
    val fullscreenViewModel:FullscreenViewModel by viewModels()
    private lateinit var fragmentDialog : FragmentItemListDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) :View? {
        fragmentDialog = FragmentItemListDialogBinding.inflate(inflater,container,false)
        //return inflater.inflate(R.layout.fragment_item_list_dialog, container, false)
        return  fragmentDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragmentDialog.apply {
            homescreen.setOnClickListener{

                /*fullscreenViewModel.setWallPaper()
                fullscreenViewModel.printValues()*/
               // fullscreenViewModel.setSTA(3)
                fullscreenViewModel.changeValue(3)


               Log.d("mikee","${fullscreenViewModel.getSTA().value}")
                dismiss()
            }

            lockscreen.setOnClickListener {

                fullscreenViewModel.STA.observe(viewLifecycleOwner){

                    Log.d("lockscreen","lock answer is $it")
                }
            }





        }



    }
}