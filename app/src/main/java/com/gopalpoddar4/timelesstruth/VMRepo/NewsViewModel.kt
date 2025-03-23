package com.gopalpoddar4.timelesstruth.VMRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gopalpoddar4.timelesstruth.Model.Article
import com.gopalpoddar4.timelesstruth.VMRepo.NewsRepo
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepo): ViewModel(){



    fun getEveryNews(query: String,language: String,sortby: String,apikey: String){
        viewModelScope.launch {
            repository.getEveryNews(query,language,sortby,apikey)
        }
    }
    val news: LiveData<List<Article>> = repository.news
}