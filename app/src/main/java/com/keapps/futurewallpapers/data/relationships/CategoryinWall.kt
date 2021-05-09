package com.keapps.futurewallpapers.data.relationships

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.WallPaperModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryinWall(
    @Embedded val category: Categories,
    @Relation(
        parentColumn = "catId",
        entityColumn = "wallId",
        associateBy = Junction(WallpaperCategorycrossref::class)
    )
    val wallpapers:List<WallPaperModel>
):Parcelable