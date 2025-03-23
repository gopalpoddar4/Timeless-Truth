package com.gopalpoddar4.timelesstruth.VMRepo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gopalpoddar4.timelesstruth.VMRepo.NewsRepo

class NewsVMFactory(private val newsRepo: NewsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(newsRepo) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}