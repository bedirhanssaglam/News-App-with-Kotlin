package com.android.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.newsapp.domain.models.Article
import com.android.newsapp.presentation.util.constants.DatabaseConstants
import com.android.newsapp.presentation.util.constants.NetworkConstants
import com.android.newsapp.presentation.util.helpers.Converters

/**
 * The [Room] database for the application.
 * This class represents the database and serves as the main access point for the underlying connection to the app's persisted data.
 */
@Database(entities = [Article::class], version = NetworkConstants.PAGE_DEFAULT_VALUE)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    /**
     * Returns an instance of the ArticleDao.
     *
     * @return The DAO for the Article entity.
     */
    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null

        private val LOCK = Any()

        /**
         * Singleton pattern to ensure only one instance of the database is created.
         *
         * @param [context] The application context.
         * @return The singleton instance of the ArticleDatabase.
         */
        operator fun invoke(context: Context): ArticleDatabase =
            instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }

        /**
         * Creates the database instance.
         *
         * @param [context] The application context.
         * @return A new instance of the ArticleDatabase.
         */
        private fun createDatabase(context: Context): ArticleDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                DatabaseConstants.DATABASE_NAME
            ).build()
    }
}
