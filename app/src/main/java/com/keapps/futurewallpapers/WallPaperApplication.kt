package com.keapps.futurewallpapers

import android.app.Application
import com.keapps.futurewallpapers.data.WallPaperRoomDatabase
import com.keapps.futurewallpapers.repository.WallpapersRepo
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
@HiltAndroidApp
class WallPaperApplication:Application() {
   /* val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {
        WallPaperRoomDatabase.getDatabase(this)

    }
    val repository by lazy {
        WallpapersRepo(database.wallDao())
    }
    */

}