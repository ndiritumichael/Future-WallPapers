package com.keapps.futurewallpapers

import android.app.Application
import com.keapps.futurewallpapers.data.WallPaperRoomDatabase
import com.keapps.futurewallpapers.repository.WallpapersRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WallPaperApplication:Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {
        WallPaperRoomDatabase.getDatabase(this,applicationScope)

    }
    val repository by lazy {
        WallpapersRepo(database.wallDao())
    }
}