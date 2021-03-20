package com.keapps.futurewallpapers.repository


import com.keapps.futurewallpapers.data.WallDAO
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow

class WallpapersRepo (val wallpaperdao : WallDAO){
    val allWallappers
    get() = wallpaperdao.getAllWallpapers()

val favWallPapers
get() = wallpaperdao.getFavorites()

    suspend fun insertWallpaper(wallpaper: WallPaperModel){
        wallpaperdao.insert(wallpaper)
    }

    suspend fun updateData(wallPaper: WallPaperModel) {
        wallpaperdao.update(wallPaper)

    }

   suspend fun fullWallpaper(id: Int) = wallpaperdao.getWallPaper(id)



}