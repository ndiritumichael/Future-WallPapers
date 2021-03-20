package com.keapps.futurewallpapers

import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.keapps.futurewallpapers.databinding.ActivityMainBinding
import com.keapps.futurewallpapers.ui.wallpapers.WallViewModelFactory
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersViewModel

class MainActivity : AppCompatActivity() {
 private lateinit var mainActivityMainBinding: ActivityMainBinding
 private val wallpapersViewModel : WallpapersViewModel by viewModels {
     WallViewModelFactory((application as WallPaperApplication).repository)
 }
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

        navView.setupWithNavController(navController)
        wallpapersViewModel.wallpaperlist.observe(this){
            it.forEach{
                Log.d("MainActivity","data is $it")
            }
        }
    }
}