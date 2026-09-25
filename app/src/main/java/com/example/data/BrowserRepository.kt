package com.example.data

import kotlinx.coroutines.flow.Flow

class BrowserRepository(private val dao: BrowserDao) {
    val allBookmarks: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()
    val allCachedArticles: Flow<List<CachedArticleEntity>> = dao.getAllCachedArticles()

    suspend fun insertBookmark(title: String, url: String) {
        dao.insertBookmark(BookmarkEntity(title = title, url = url))
    }

    suspend fun deleteBookmark(id: Long) {
        dao.deleteBookmark(id)
    }

    suspend fun cacheArticle(title: String, url: String, snippet: String, contentHtml: String, type: String = "article") {
        dao.insertCachedArticle(CachedArticleEntity(title = title, url = url, snippet = snippet, contentHtml = contentHtml, type = type))
    }

    suspend fun deleteCachedArticle(id: Long) {
        dao.deleteCachedArticle(id)
    }

    suspend fun getBrowserSession(): BrowserSessionEntity? {
        return dao.getBrowserSession()
    }

    suspend fun saveBrowserSession(url: String, title: String) {
        dao.saveBrowserSession(BrowserSessionEntity(id = 1, lastUrl = url, lastTitle = title))
    }
}
