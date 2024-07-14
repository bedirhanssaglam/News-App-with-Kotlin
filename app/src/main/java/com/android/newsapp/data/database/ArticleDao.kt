package com.android.newsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.newsapp.domain.models.Article

/**
 * Data Access Object (DAO) for the Article entity.
 * Provides methods to perform CRUD operations on the Article database table.
 */
@Dao
interface ArticleDao {
    /**
     * Inserts or updates an article in the database.
     * If the article already exists, it replaces the old data.
     *
     * @param article The article to be upserted.
     * @return The row ID of the inserted article.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    /**
     * Retrieves all articles from the database.
     *
     * @return A LiveData list of all articles.
     */
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

     /**
     * Deletes a specific article from the database.
     *
     * @param article The article to be deleted.
     */
    @Delete
    suspend fun deleteArticle(article: Article)
}