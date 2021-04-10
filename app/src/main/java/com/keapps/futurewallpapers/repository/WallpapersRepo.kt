package com.keapps.futurewallpapers.repository


import com.keapps.futurewallpapers.data.WallDAO
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WallpapersRepo @Inject constructor(val wallpaperdao : WallDAO){
    val allWallappers
    get() = wallpaperdao.getAllWallpapers()

  /*  val categories
    get() = wallpaperdao.getAllCategories()*/

val favWallPapers
get() = wallpaperdao.getFavorites()

    suspend fun insertWallpaper(wallpaper: WallPaperModel){
        wallpaperdao.insert(wallpaper)
    }

    suspend fun updateData(wallPaper: WallPaperModel) {
        wallpaperdao.update(wallPaper)

    }

   suspend fun fullWallpaper(id: Int) = wallpaperdao.getWallPaper(id)

    suspend fun  getwallinCat(category: Int)= wallpaperdao.getwallpapersCategory(category)

    suspend fun getcatinWall(id: Int)= wallpaperdao.getCategoryinWall(id)




   /*suspend fun getCategory(categories: Categories) {
 wallpaperdao.getCategoryItems(categories)
    }*/


}