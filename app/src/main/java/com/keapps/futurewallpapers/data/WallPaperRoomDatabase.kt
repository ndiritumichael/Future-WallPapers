
package com.keapps.futurewallpapers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.ui.wallpapers.WallpapersFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WallPaperModel::class],version = 1,exportSchema = false)
abstract class WallPaperRoomDatabase : RoomDatabase() {

    abstract fun wallDao():WallDAO

    companion object{
        @Volatile
        private var INSTANCE : WallPaperRoomDatabase? = null
        fun  getDatabase(context : Context,scope: CoroutineScope):WallPaperRoomDatabase{
            return INSTANCE ?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WallPaperRoomDatabase::class.java,
                    "wallpaper_database"
                ).addCallback(RoomWallCallBack(scope))
                        .build()
                INSTANCE = instance
               instance

            }


        }
    }
    private class RoomWallCallBack(private val scope:CoroutineScope):RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {database ->
                scope.launch {
                    populateDatabase(database.wallDao())
                }

            }
        }

        private suspend fun populateDatabase(wallDao: WallDAO) {
           // wallDao.deleteAll()

            val desc = WallPaperModel(1,"Pubg","https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","MIKEWIL",true)
            val desc1 = WallPaperModel(2,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc2 = WallPaperModel(3,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc3 = WallPaperModel(4,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc4 = WallPaperModel(5,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc5 = WallPaperModel(6,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc6 = WallPaperModel(7,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc7 = WallPaperModel(8,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc8 = WallPaperModel(9,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc9 = WallPaperModel(10,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc10 = WallPaperModel(11,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)
            val desc11 = WallPaperModel(12,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false)

            val list = listOf<WallPaperModel>(desc,desc1,
                    desc2,
                    desc3,desc4,desc5,desc6,desc7,desc8,desc9,desc10,desc11)


            list.forEach { wallpaper ->
                wallDao.insert(wallpaper)
            }



        }

    }
}
