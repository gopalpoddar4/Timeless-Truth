package com.gopalpoddar4.timelesstruth.VMRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gopalpoddar4.timelesstruth.API.APIinterfcae
import com.gopalpoddar4.timelesstruth.Database.NewsDao
import com.gopalpoddar4.timelesstruth.Model.Article
import com.gopalpoddar4.timelesstruth.Model.FavModel

class NewsRepo (private val newsApi: APIinterfcae,private val newsDao: NewsDao){

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> get() = _news

    private val _fav = MutableLiveData<List<FavModel>>()
    val fav: LiveData<List<FavModel>> get() = _fav


    suspend fun getEveryNews(query: String,language: String,sortby: String,apikey: String){
        val everyNewsResponce = newsApi.getEveryNews(query,language,sortby,apikey)
        _news.postValue(everyNewsResponce.articles)
    }

    suspend fun AddFavNews(favModel: FavModel){
        newsDao.AddFavNews(favModel)
    }
    suspend fun DeleteFavNews(id: Int){
        newsDao.DeleteFavNews(id)
    }

    suspend fun FetchFavNews(){
        val favNews= newsDao.FetchFavNews()
        _fav.postValue(favNews)
    }

}