package com.android.newsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.newsapp.presentation.util.constants.DatabaseConstants
import java.io.Serializable

@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
) : Serializable
