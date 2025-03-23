package com.gopalpoddar4.timelesstruth.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gopalpoddar4.timelesstruth.Model.FavModel
import org.jetbrains.annotations.NotNull

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddFavNews(favModel: FavModel)

    @Query("SELECT * FROM fav_table")
    suspend fun FetchFavNews(): List<FavModel>

   @Query("DELETE FROM fav_table WHERE id=:id")
    suspend fun DeleteFavNews(id: Int)

}