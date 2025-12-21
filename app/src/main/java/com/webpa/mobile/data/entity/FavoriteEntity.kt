package com.webpa.mobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val price: Float,
    val imageUrl: String,
    val marketplace: String
)