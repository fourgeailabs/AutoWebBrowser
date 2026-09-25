package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "browser_session")
data class BrowserSessionEntity(
    @PrimaryKey val id: Int = 1,
    val lastUrl: String,
    val lastTitle: String,
    val timestamp: Long = System.currentTimeMillis()
)
