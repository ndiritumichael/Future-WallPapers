
package com.keapps.futurewallpapers.data

import androidx.room.*
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow
@Dao
interface WallDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wallpaper:WallPaperModel):Unit

    @Query("DELETE FROM wallpaper_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM wallpaper_table ORDER BY id DESC")
     fun getAllWallpapers(): Flow<List<WallPaperModel>>


}
