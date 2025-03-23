package com.gopalpoddar4.timelesstruth.VMRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gopalpoddar4.timelesstruth.API.APIinterfcae
import com.gopalpoddar4.timelesstruth.Model.Article

class NewsRepo (private val newsApi: APIinterfcae){

    private val _news = MutableLiveData<List<Article>>()
    val news: LiveData<List<Article>> get() = _news



    suspend fun getEveryNews(query: String,language: String,sortby: String,apikey: String){
        val everyNewsResponce = newsApi.getEveryNews(query,language,sortby,apikey)
        _news.postValue(everyNewsResponce.articles)
    }

}