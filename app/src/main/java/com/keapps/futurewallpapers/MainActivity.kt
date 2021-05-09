package com.keapps.futurewallpapers

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toolbar
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.keapps.futurewallpapers.databinding.ActivityMainBinding


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
 private lateinit var mainActivityMainBinding: ActivityMainBinding

/* private val wallpapersViewModel : WallpapersViewModel by viewModels {
     WallViewModelFactory((application as WallPaperApplication).repository)
 }*/
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityMainBinding.root)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(mainActivityMainBinding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_wallpapers, R.id.navigation_collections, R.id.navigation_favorites))
        setupActionBarWithNavController(navController, appBarConfiguration)

    navView.setOnNavigationItemReselectedListener {  }
    navController.addOnDestinationChangedListener{ navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
        when(navDestination.id)
        { R.id.navigation_wallpapers, R.id.navigation_collections, R.id.navigation_favorites ->{
            mainActivityMainBinding.apply {
                navView.visibility = View.VISIBLE
                mainappbar.visibility = View.VISIBLE
           //     window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            }
        }
            R.id.singleCollection -> {
              mainActivityMainBinding.mainappbar.visibility = View.VISIBLE

            }
            else -> {
            mainActivityMainBinding.apply {
                navView.visibility = View.GONE
                mainappbar.visibility = View.GONE
            }
/*
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
           window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
           window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor= Color.WHITE
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

*/

        }
        }
    }

        navView.setupWithNavController(navController)

    }
}