package com.gopalpoddar4.timelesstruth.Model

data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)