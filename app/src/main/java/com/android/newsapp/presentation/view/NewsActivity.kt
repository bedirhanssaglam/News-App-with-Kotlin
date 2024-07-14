package com.android.newsapp.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.newsapp.R
import com.android.newsapp.databinding.ActivityNewsBinding
import com.android.newsapp.data.database.ArticleDatabase
import com.android.newsapp.data.repository.NewsRepository
import com.android.newsapp.presentation.viewmodel.NewsViewModel
import com.android.newsapp.presentation.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private var binding: ActivityNewsBinding? = null
    var newsViewModel: NewsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // Initialize NewsRepository with ArticleDatabase dependency
        val newsRepository = NewsRepository(ArticleDatabase(this))

        // Create ViewModelProviderFactory instance with Application context and NewsRepository
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)

        // Initialize NewsViewModel using ViewModelProvider with factory
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel::class.java]

        // Set up bottom navigation with NavController
        supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)?.let { fragment ->
            val navController: NavController = fragment.findNavController()
            binding!!.bottomNavigationView.setupWithNavController(navController)
        }
    }

    override fun onDestroy() {
        binding = null
        newsViewModel = null
        super.onDestroy()
    }
}
