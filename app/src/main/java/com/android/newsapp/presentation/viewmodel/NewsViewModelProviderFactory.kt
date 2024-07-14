package com.android.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.newsapp.data.repository.NewsRepository

/**
 * ViewModelProvider.Factory implementation for NewsViewModel.
 *
 * @param app Application context for creating the ViewModel.
 * @param newsRepository Repository dependency to be injected into NewsViewModel.
 */
class NewsViewModelProviderFactory(
    private val app: Application,
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    /**
     * Creates an instance of the specified ViewModel.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return The created ViewModel instance.
     * @throws IllegalArgumentException If the ViewModel class is unknown.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(app, newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
