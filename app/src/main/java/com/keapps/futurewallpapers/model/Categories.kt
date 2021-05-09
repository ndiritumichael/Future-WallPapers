package com.keapps.futurewallpapers.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Categories(
    @PrimaryKey
    val catId :Int,
    val categories:String
):Parcelable