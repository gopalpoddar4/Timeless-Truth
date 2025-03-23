package com.gopalpoddar4.timelesstruth

import android.app.Application
import com.gopalpoddar4.timelesstruth.API.APIinterfcae
import com.gopalpoddar4.timelesstruth.API.RetrofitHelper
import com.gopalpoddar4.timelesstruth.Database.NewsDao
import com.gopalpoddar4.timelesstruth.Database.NewsDatabase
import com.gopalpoddar4.timelesstruth.VMRepo.NewsRepo

class NewsApplication(): Application() {

        lateinit var database: NewsDatabase
        lateinit var repo: NewsRepo
        lateinit var newsDao: NewsDao

    override fun onCreate() {
        super.onCreate()
        val database = NewsDatabase.getInstance(applicationContext)
        val api = RetrofitHelper.getAPI().create(APIinterfcae::class.java)
        newsDao = database.newsDao()
        repo = NewsRepo(api,newsDao)
    }

}