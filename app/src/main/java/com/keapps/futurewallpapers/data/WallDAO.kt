
package com.keapps.futurewallpapers.data

import androidx.room.*
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow
@Dao
interface WallDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wallpaper:WallPaperModel)

    @Query("DELETE FROM wallpaper_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM wallpaper_table ORDER BY id DESC")
     fun getAllWallpapers(): Flow<List<WallPaperModel>>

     @Query("SELECT * FROM wallpaper_table WHERE favorites = 1")
     fun getFavorites():Flow<List<WallPaperModel>>


}
