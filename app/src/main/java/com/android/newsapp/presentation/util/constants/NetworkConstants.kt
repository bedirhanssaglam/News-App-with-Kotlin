package com.android.newsapp.presentation.util.constants

/**
 * Constants related to network operations, API endpoints, and parameters.
 */
object NetworkConstants {
    // API paths
    const val TOP_HEADLINES_PATH = "top-headlines"
    const val EVERYTHING_PATH = "everything"

    // Query parameters
    const val COUNTRY_QUERY_KEY = "country"
    const val PAGE_QUERY_KEY = "page"
    const val API_KEY_QUERY_KEY = "apiKey"
    const val SEARCH_QUERY_KEY = "q"

    // Default values
    const val API_KEY = "<YOUR_API_KEY>"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val QUERY_PAGE_SIZE = 20

    const val COUNTRY_DEFAULT_VALUE = "us"
    const val PAGE_DEFAULT_VALUE = 1
}
