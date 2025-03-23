package com.gopalpoddar4.timelesstruth.Database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gopalpoddar4.timelesstruth.Model.FavModel

@Database(entities = [FavModel::class], version = 1)
abstract class NewsDatabase(): RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDatabase?=null

        fun getInstance(context: android.content.Context): NewsDatabase{
            return INSTANCE?:synchronized (this){
                val intance = Room.databaseBuilder(context, NewsDatabase::class.java,"newsdb").fallbackToDestructiveMigration().build()
                INSTANCE=intance
                intance
            }
        }
    }
}