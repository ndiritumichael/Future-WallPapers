package com.keapps.futurewallpapers

import android.app.Application
import com.keapps.futurewallpapers.data.WallPaperRoomDatabase
import com.keapps.futurewallpapers.repository.WallpapersRepo

class WallPaperApplication:Application() {
    val database by lazy {
        WallPaperRoomDatabase.getDatabase(this)

    }
    val repository by lazy {
        WallpapersRepo(database.wallDao())
    }
}