package com.keapps.futurewallpapers.data.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel

data class WallpaperinCategories (

    @Embedded val wallPaperModel: WallPaperModel,
    @Relation(
        entityColumn = "catId",
        parentColumn = "wallId",
        associateBy = Junction(WallpaperCategorycrossref::class)
    )
    val categories: List<Categories>
)