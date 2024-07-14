package com.android.newsapp.data.network

import com.android.newsapp.domain.models.News
import com.android.newsapp.presentation.util.constants.AppConstants
import com.android.newsapp.presentation.util.constants.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service interface for defining API endpoints related to news.
 */
interface NewsService {
    /**
     * Fetches top headlines based on the specified country code and page number.
     *
     * @param countryCode The country code for fetching headlines.
     * @param pageNumber The page number for pagination.
     * @param apiKey The API key for authentication.
     * @return The response containing the news data.
     */
    @GET(NetworkConstants.TOP_HEADLINES_PATH)
    suspend fun getHeadlines(
        @Query(NetworkConstants.COUNTRY_QUERY_KEY) countryCode: String = NetworkConstants.COUNTRY_DEFAULT_VALUE,
        @Query(NetworkConstants.PAGE_QUERY_KEY) pageNumber: Int = NetworkConstants.PAGE_DEFAULT_VALUE,
        @Query(NetworkConstants.API_KEY_QUERY_KEY) apiKey: String = NetworkConstants.API_KEY,
    ): Response<News>

    /**
     * Searches news based on the provided query and page number.
     *
     * @param searchQuery The search query for fetching news.
     * @param pageNumber The page number for pagination.
     * @param apiKey The API key for authentication.
     * @return The response containing the news data.
     */
    @GET(NetworkConstants.EVERYTHING_PATH)
    suspend fun searchNews(
        @Query(NetworkConstants.SEARCH_QUERY_KEY) searchQuery: String,
        @Query(NetworkConstants.PAGE_QUERY_KEY) pageNumber: Int = NetworkConstants.PAGE_DEFAULT_VALUE,
        @Query(NetworkConstants.API_KEY_QUERY_KEY) apiKey: String = NetworkConstants.API_KEY,
    ): Response<News>
}
