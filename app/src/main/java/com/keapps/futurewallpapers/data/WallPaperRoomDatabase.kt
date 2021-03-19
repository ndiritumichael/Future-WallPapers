
package com.keapps.futurewallpapers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersFragmentDirections

@Database(entities = arrayOf(WallPaperModel::class),version = 1,exportSchema = false)
abstract class WallPaperRoomDatabase : RoomDatabase() {

    abstract fun wallDao():WallDAO

    companion object{
        @Volatile
        private var INSTANCE : WallPaperRoomDatabase? = null
        fun  getDatabase(context : Context):WallPaperRoomDatabase{
            return INSTANCE ?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WallPaperRoomDatabase::class.java,
                    "wallpaper_database"
                ).build()
                INSTANCE = instance
               instance

            }


        }
    }
}
