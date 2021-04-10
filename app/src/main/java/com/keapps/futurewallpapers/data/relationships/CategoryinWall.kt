package com.keapps.futurewallpapers.data.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel

data class CategoryinWall(
    @Embedded val category: Categories,
    @Relation(
        parentColumn = "catId",
        entityColumn = "wallId",
        associateBy = Junction(WallpaperCategorycrossref::class)
    )
    val wallpapers:List<WallPaperModel>
)