package com.android.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.android.newsapp.data.network.NetworkManager
import com.android.newsapp.data.network.NewsService
import com.android.newsapp.data.database.ArticleDatabase
import com.android.newsapp.domain.models.Article
import com.android.newsapp.domain.models.News
import retrofit2.Response

/**
 * Repository class for managing data operations.
 * It abstracts the data sources (network and database) and provides a clean API for data access.
 *
 * @param database The [ArticleDatabase] instance for local data operations.
 */
class NewsRepository(private val database: ArticleDatabase) {
    private val service: NewsService = NetworkManager.getInstance().createService()

    /**
     * Fetches top headlines from the network.
     *
     * @param pageNumber The page number for pagination.
     * @return The response containing the news data.
     */
    suspend fun getHeadlines(pageNumber: Int): Response<News> = service.getHeadlines(pageNumber = pageNumber)


    /**
     * Searches news from the network based on the provided query and page number.
     *
     * @param searchQuery The search query for fetching news.
     * @param pageNumber The page number for pagination.
     * @return The response containing the news data.
     */
    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<News> =
        service.searchNews(searchQuery, pageNumber)

    /**
     * Inserts or updates an article in the local database.
     *
     * @param article The article to be upserted.
     * @return The row ID of the inserted article.
     */
    suspend fun upsert(article: Article): Long = database.getArticleDao().upsert(article)

    /**
     * Retrieves all favorite articles from the local database.
     *
     * @return A LiveData list of all favorite articles.
     */
    fun getFavoriteNews(): LiveData<List<Article>> = database.getArticleDao().getAllArticles()

    /**
     * Deletes a specific article from the local database.
     *
     * @param article The article to be deleted.
     */
    suspend fun deleteArticle(article: Article): Unit = database.getArticleDao().deleteArticle(article)
}