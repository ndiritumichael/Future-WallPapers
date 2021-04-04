
package com.keapps.futurewallpapers.data

import androidx.room.*
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow
@Dao
interface WallDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallpaper:WallPaperModel)

    @Query("DELETE FROM wallpaper_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM wallpaper_table ORDER BY id DESC")
 fun getAllWallpapers(): Flow<List<WallPaperModel>>

     @Query("SELECT * FROM wallpaper_table WHERE favorites = 1")
     fun getFavorites():Flow<List<WallPaperModel>>

     @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend fun update(wallPaper: WallPaperModel)

   @Query("SELECT * FROM wallpaper_table WHERE id = :wallid")
   suspend fun getWallPaper( wallid: Int):WallPaperModel?

  /* @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategories(categories: kotlin.collections.ArrayList<kotlin.String>)*/
/*

   @Query("SELECT * FROM Categories")
    fun getAllCategories():Flow<List<String>>
*/

   /* @Query("SELECT * FROM wallpaper_table WHERE category = :categories")
      suspend  fun getCategoryItems(categories: Categories)*/


}
