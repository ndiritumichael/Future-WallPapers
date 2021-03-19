package com.keapps.futurewallpapers.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wallpaper_table")
data class WallPaperModel(
    @PrimaryKey
    val id : Int,
    val Title : String,
    val lowHd :  String,
    val fullHd : String,
    val authorName : String,
    val favorites : Boolean
)
