package com.android.newsapp.domain.models

import androidx.lifecycle.MutableLiveData

data class News(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int,
)
