package com.webpa.mobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.webpa.mobile.data.dao.FavoritesDao
import com.webpa.mobile.data.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}
