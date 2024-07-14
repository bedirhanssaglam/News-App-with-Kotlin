package com.android.newsapp.data.network

import com.android.newsapp.presentation.util.constants.NetworkConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton class responsible for managing the [Retrofit] instance for network operations.
 */
class NetworkManager {

    private val retrofit: Retrofit

    companion object {
        private const val NETWORK_TIMEOUT = 30L

        @Volatile
        private var INSTANCE: NetworkManager? = null

        /**
         * Provides the singleton instance of [NetworkManager].
         *
         * @return The singleton instance of [NetworkManager].
         */
        fun getInstance(): NetworkManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: NetworkManager().also { INSTANCE = it }
            }
        }
    }

    init {
        // Configure OkHttpClient with timeout settings and logging interceptor
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        // Initialize Retrofit with the base URL and Gson converter
        retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Creates and returns the NewsService instance for API calls.
     *
     * @return The [NewsService] instance.
     */
    fun createService(): NewsService = retrofit.create(NewsService::class.java)
}
