package com.keapps.futurewallpapers.repository


import com.keapps.futurewallpapers.data.WallDAO
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow

class WallpapersRepo (val wallpaperdao : WallDAO){
    val allWallappers
    get() = wallpaperdao.getAllWallpapers()


    val desc = WallPaperModel(1,"Pubg","https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","https://images.pexels.com/photos/1366919/pexels-photo-1366919.jpeg","MIKEWIL")
    val desc1 = WallPaperModel(2,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc2 = WallPaperModel(3,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc3 = WallPaperModel(4,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc4 = WallPaperModel(5,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc5 = WallPaperModel(6,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc6 = WallPaperModel(7,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc7 = WallPaperModel(8,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc8 = WallPaperModel(9,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc9 = WallPaperModel(10,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc10 = WallPaperModel(11,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")
    val desc11 = WallPaperModel(12,"Pubg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","https://images.pexels.com/photos/5481878/pexels-photo-5481878.jpeg","MIKEWIL")

    var list = listOf<WallPaperModel>(desc,desc1,
        desc2,
        desc3,desc4,desc5,desc6,desc7,desc8,desc9,desc10,desc11)

    suspend fun insertWallpaper(wallpaper: WallPaperModel){
        wallpaperdao.insert(wallpaper)
    }

}