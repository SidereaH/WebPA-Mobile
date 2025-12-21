package com.webpa.mobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.webpa.mobile.data.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    fun observeFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: Long): Boolean
}
