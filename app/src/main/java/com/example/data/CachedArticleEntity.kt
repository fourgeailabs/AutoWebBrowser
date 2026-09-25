package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_articles")
data class CachedArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val url: String,
    val snippet: String,
    val contentHtml: String,
    val type: String = "article", // "article" or "video"
    val timestamp: Long = System.currentTimeMillis()
)
