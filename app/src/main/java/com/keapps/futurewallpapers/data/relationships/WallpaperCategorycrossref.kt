package com.keapps.futurewallpapers.data.relationships

import androidx.room.Entity


@Entity(primaryKeys = ["wallId","catId"])
data class WallpaperCategorycrossref (
val wallId : Int,
val catId : Int
        )