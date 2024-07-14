package com.android.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.databinding.ItemNewsBinding
import com.android.newsapp.domain.models.Article
import com.bumptech.glide.Glide

/**
 * Adapter class for displaying news articles in a RecyclerView.
 * Utilizes AsyncListDiffer for efficient list updates.
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // DiffUtil callback for comparing old and new items in the list
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Compare items based on their URL as it uniquely identifies an article
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            // Compare the content of the items to determine if they are the same
            return oldItem == newItem
        }
    }

    // AsyncListDiffer to handle list updates asynchronously
    val differ = AsyncListDiffer(this, differCallback)

    /**
     * ViewHolder class for holding and binding article data to views.
     */
    inner class ArticleViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds an article to the UI elements.
         *
         * @param article The article to be displayed
         */
        fun bind(article: Article) {
            binding.apply {
                // Load the article image using Glide
                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .into(articleImage)

                // Set text fields with article data
                articleSource.text = article.source.name
                articleTitle.text = article.title
                articleDescription.text = article.description
                articleDateTime.text = article.publishedAt

                // Set click listener to trigger the item click callback
                root.setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            }
        }
    }

    /**
     * Inflates the item view and creates a ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    /**
     * Returns the total number of items in the list.
     */
    override fun getItemCount(): Int = differ.currentList.size

    /**
     * Binds the data to the ViewHolder at the specified position.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    // Listener for item click events
    private var onItemClickListener: ((Article) -> Unit)? = null

    /**
     * Sets the item click listener.
     *
     * @param listener The callback to be invoked on item click
     */
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
