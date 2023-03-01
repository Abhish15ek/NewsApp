package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NewsItem")
data class NewsModel (
    @PrimaryKey(autoGenerate = false)
//    val cnt: Int,
    val title: String,
    val author: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,

    )