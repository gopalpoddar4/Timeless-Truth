package com.gopalpoddar4.timelesstruth.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class FavModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imageUrl:String,
    val newsTitle: String,
    val newsSource:String,
    val newsUrl: String
)
