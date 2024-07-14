package com.android.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.newsapp.domain.models.Article
import com.android.newsapp.domain.models.News
import com.android.newsapp.data.repository.NewsRepository
import com.android.newsapp.presentation.base.BaseViewModel
import com.android.newsapp.presentation.base.Resource
import com.android.newsapp.presentation.util.constants.NetworkConstants
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    application: Application,
    private val newsRepository: NewsRepository
) : BaseViewModel(application) {

    private val _headlines = MutableLiveData<Resource<News>>()
    val headlines: LiveData<Resource<News>> = _headlines

    private val _searchNews = MutableLiveData<Resource<News>>()
    val searchNews: LiveData<Resource<News>> = _searchNews

    var headlinesPage = NetworkConstants.PAGE_DEFAULT_VALUE
    var searchNewsPage = NetworkConstants.PAGE_DEFAULT_VALUE
    private var headlinesResponse: News? = null
    private var searchNewsResponse: News? = null
    private var newSearchQuery: String? = null
    private var oldSearchQuery: String? = null

    init {
        setHeadlines()
    }

    /**
     * Fetch headlines from the repository and update _headlines LiveData with result.
     */
    fun setHeadlines() {
        viewModelScope.launch { fetchHeadlines() }
    }

    /**
     * Fetch searched news based on searchQuery and update _searchNews LiveData with result.
     */
    fun setSearchedNews(searchQuery: String) {
        viewModelScope.launch { fetchSearchedNews(searchQuery) }
    }

    /**
     * Add article to favorites using newsRepository.
     */
    fun addToFavorites(article: Article) {
        viewModelScope.launch { newsRepository.upsert(article) }
    }

    /**
     * Get list of favorite news articles from newsRepository.
     */
    fun getFavoriteNews(): LiveData<List<Article>> = newsRepository.getFavoriteNews()

    /**
     * Delete article from favorites using newsRepository.
     */
    fun deleteArticle(article: Article) {
        viewModelScope.launch { newsRepository.deleteArticle(article) }
    }

    /**
     * Fetch headlines from the API and handle API response using handleApiCall from BaseViewModel.
     */
    private suspend fun fetchHeadlines() {
        handleApiCall(
            liveData = _headlines,
            apiCall = { newsRepository.getHeadlines(headlinesPage) }
        ) { resultResponse ->
            headlinesPage++
            if (headlinesResponse == null) {
                headlinesResponse = resultResponse
            } else {
                headlinesResponse?.articles?.addAll(resultResponse.articles)
            }
            _headlines.postValue(Resource.Success(headlinesResponse ?: resultResponse))
        }
    }

    /**
     * Fetch searched news from the API based on searchQuery and handle API response using handleApiCall from BaseViewModel.
     */
    private suspend fun fetchSearchedNews(searchQuery: String) {
        newSearchQuery = searchQuery
        if (newSearchQuery != oldSearchQuery) {
            searchNewsPage = NetworkConstants.PAGE_DEFAULT_VALUE
            oldSearchQuery = newSearchQuery
            searchNewsResponse = null
        }

        handleApiCall(
            liveData = _searchNews,
            apiCall = { newsRepository.searchNews(searchQuery, searchNewsPage) }
        ) { resultResponse ->
            searchNewsPage++
            if (searchNewsResponse == null) {
                searchNewsResponse = resultResponse
            } else {
                searchNewsResponse?.articles?.addAll(resultResponse.articles)
            }
            _searchNews.postValue(Resource.Success(searchNewsResponse ?: resultResponse))
        }
    }
}
