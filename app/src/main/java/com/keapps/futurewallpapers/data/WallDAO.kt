
package com.keapps.futurewallpapers.data

import androidx.room.*
import com.keapps.futurewallpapers.data.relationships.CategoryinWall
import com.keapps.futurewallpapers.data.relationships.WallpaperCategorycrossref
import com.keapps.futurewallpapers.data.relationships.WallpaperinCategories
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.coroutines.flow.Flow
@Dao
interface WallDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wallpaper:WallPaperModel)

    @Query("DELETE FROM wallpaper_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM wallpaper_table ORDER BY wallId ASC")
 fun getAllWallpapers(): Flow<List<WallPaperModel>>

     @Query("SELECT * FROM wallpaper_table WHERE favorites = 1")
     fun getFavorites():Flow<List<WallPaperModel>>

     @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend fun update(wallPaper: WallPaperModel)

   @Query("SELECT * FROM wallpaper_table WHERE wallId = :wallid")
   suspend fun getWallPaper( wallid: Int):WallPaperModel?

 @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategories(categories: Categories)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCrossRef(ref:WallpaperCategorycrossref)
/*

   @Query("SELECT * FROM Categories")
    fun getAllCategories():Flow<List<String>>
*/

   /* @Query("SELECT * FROM wallpaper_table WHERE category = :categories")
      suspend  fun getCategoryItems(categories: Categories)*/
   @Transaction
   @Query("SELECT * FROM Categories WHERE  catId = :category")
   suspend fun getwallpapersCategory(category : Int): CategoryinWall

    @Transaction
    @Query("SELECT * FROM wallpaper_table WHERE  wallId = :wallpaper")
    suspend fun getCategoryinWall(wallpaper : Int): WallpaperinCategories

    @Transaction
    @Query("SELECT * FROM Categories")
    fun getAllCategories():Flow<List<CategoryinWall>>


}
