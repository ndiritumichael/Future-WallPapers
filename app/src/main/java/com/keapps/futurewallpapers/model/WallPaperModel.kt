package com.keapps.futurewallpapers.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "wallpaper_table")
data class WallPaperModel(
        @PrimaryKey
    val wallId : Int = 0,
        val title : String = "",
        val lowHd :  String = "",
        val fullHd : String="",
        val authorName : String = "",
        var favorites : Boolean = false,
        val category: String = "Pubg"
):Parcelable


