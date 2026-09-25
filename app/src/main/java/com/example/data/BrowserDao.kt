package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BrowserDao {
    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmark(id: Long)

    @Query("SELECT * FROM cached_articles ORDER BY timestamp DESC")
    fun getAllCachedArticles(): Flow<List<CachedArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedArticle(article: CachedArticleEntity)

    @Query("DELETE FROM cached_articles WHERE id = :id")
    suspend fun deleteCachedArticle(id: Long)

    @Query("SELECT * FROM browser_session WHERE id = 1")
    suspend fun getBrowserSession(): BrowserSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBrowserSession(session: BrowserSessionEntity)
}
