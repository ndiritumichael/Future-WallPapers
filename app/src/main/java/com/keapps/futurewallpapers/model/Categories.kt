package com.keapps.futurewallpapers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categories(
    @PrimaryKey
    val categories:String
)