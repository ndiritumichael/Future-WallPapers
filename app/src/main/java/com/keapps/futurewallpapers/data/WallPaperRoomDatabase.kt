
package com.keapps.futurewallpapers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.keapps.futurewallpapers.data.relationships.WallpaperCategorycrossref
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [WallPaperModel::class, WallpaperCategorycrossref::class,Categories::class],version = 3,exportSchema = false)

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
                ).fallbackToDestructiveMigration()
                  //  .addCallback(RoomWallCallBack())

                        .build()
                INSTANCE = instance
               instance

            }



        }
    }
     private class RoomWallCallBack:RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {database ->
                GlobalScope.launch(Dispatchers.IO) {
                    populateDatabase(database.wallDao())

                    populateCategories(database.wallDao())
                    populateCrossRef(database.wallDao())
                }

            }
        }

        private suspend fun populateCategories(wallDao: WallDAO){
            var id =1
            val category = arrayListOf(
            "Mountains",
            "Minimalist",
            "Art",
            "Forests",
            "Digital",
               "Heroes",
               "Wildlife",
                "Pubg"

            )
            category.forEach{
                wallDao.insertCategories(Categories(id,it))
                id+=1
            }

        }

         private suspend fun populateCrossRef(wallDao: WallDAO){
             val ref = arrayListOf(
                 WallpaperCategorycrossref(1,1),
                 WallpaperCategorycrossref(1,2),
                 WallpaperCategorycrossref(1,3),
                 WallpaperCategorycrossref(2,4),
                 WallpaperCategorycrossref(3,1),
                 WallpaperCategorycrossref(5,4),
                 WallpaperCategorycrossref(4,5),
             )
             ref.forEach {
                 wallDao.insertCrossRef(it)
             }
         }

        private suspend fun populateDatabase(wallDao: WallDAO) {
           // wallDao.deleteAll()
            val desc = WallPaperModel(1,"Pubg",
                "https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","MIKEWIL",true,
                "Mountains")
            val desc1 = WallPaperModel(2,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
                "Game")
            val desc2 = WallPaperModel(3,"Pubg",
                "https://images.pexels.com/photos/2406450/pexels-photo-2406450.jpeg",
                "https://images.pexels.com/photos/2406450/pexels-photo-2406450.jpeg","MIKEWIL",false,
              "Pubg")
            val desc3 = WallPaperModel(4,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
             "Game")
            val desc4 = WallPaperModel(5,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
               "War")
            val desc5 = WallPaperModel(6,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
               "Pubg")
            val desc6 = WallPaperModel(7,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
               "game")
            val desc7 = WallPaperModel(8,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
                "Game")
            val desc8 = WallPaperModel(9,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
            "Game")
            val desc9 = WallPaperModel(10,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
              "Game")
            val desc10 = WallPaperModel(11,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
              "Game")
            val desc11 = WallPaperModel(12,"Pubg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg",
                "https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL",false,
              "Game")

            val list = listOf<WallPaperModel>(desc,desc1,
                    desc2,
                    desc3,desc4,desc5,desc6,desc7,desc8,desc9,desc10,desc11)


            list.forEach { wallpaper ->
                wallDao.insert(wallpaper)
            }



        }

    }
}
