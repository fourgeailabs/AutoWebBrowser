package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BookmarkEntity
import com.example.data.BrowserDatabase
import com.example.data.BrowserRepository
import com.example.data.CachedArticleEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BrowserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BrowserRepository

    val bookmarks: StateFlow<List<BookmarkEntity>>
    val cachedArticles: StateFlow<List<CachedArticleEntity>>

    private val _currentUrl = MutableStateFlow("https://www.google.com")
    val currentUrl: StateFlow<String> = _currentUrl.asStateFlow()

    private val _currentTitle = MutableStateFlow("Google")
    val currentTitle: StateFlow<String> = _currentTitle.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int> = _progress.asStateFlow()

    private val _isDarkMode = MutableStateFlow(true) // Default dark mode for driving / auto
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _voicePrompt = MutableStateFlow("")
    val voicePrompt: StateFlow<String> = _voicePrompt.asStateFlow()

    init {
        val dao = BrowserDatabase.getDatabase(application).browserDao()
        repository = BrowserRepository(dao)

        bookmarks = repository.allBookmarks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        cachedArticles = repository.allCachedArticles.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        // Restore last browser session
        viewModelScope.launch {
            val session = repository.getBrowserSession()
            if (session != null && session.lastUrl.isNotBlank()) {
                _currentUrl.value = session.lastUrl
                _currentTitle.value = session.lastTitle
            }
        }
    }

    fun loadUrl(url: String) {
        var formattedUrl = url.trim()
        if (!formattedUrl.startsWith("http://") && !formattedUrl.startsWith("https://")) {
            if (formattedUrl.contains(".") && !formattedUrl.contains(" ")) {
                formattedUrl = "https://$formattedUrl"
            } else {
                formattedUrl = "https://www.google.com/search?q=" + android.net.Uri.encode(formattedUrl)
            }
        }
        _currentUrl.value = formattedUrl
        saveSession(formattedUrl, _currentTitle.value)
    }

    fun updateTitle(title: String) {
        if (title.isNotBlank()) {
            _currentTitle.value = title
            saveSession(_currentUrl.value, title)
        }
    }

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    fun setProgress(prog: Int) {
        _progress.value = prog
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    fun addBookmark(title: String, url: String) {
        viewModelScope.launch {
            repository.insertBookmark(title.ifBlank { url }, url)
        }
    }

    fun deleteBookmark(id: Long) {
        viewModelScope.launch {
            repository.deleteBookmark(id)
        }
    }

    fun cacheCurrentPage(title: String, url: String, snippet: String, htmlContent: String, type: String = "article") {
        viewModelScope.launch {
            repository.cacheArticle(title, url, snippet, htmlContent, type)
        }
    }

    fun deleteCachedArticle(id: Long) {
        viewModelScope.launch {
            repository.deleteCachedArticle(id)
        }
    }

    private fun saveSession(url: String, title: String) {
        viewModelScope.launch {
            repository.saveBrowserSession(url, title)
        }
    }

    fun saveCurrentSession(url: String, title: String) {
        if (url.isNotBlank()) {
            _currentUrl.value = url
            _currentTitle.value = title.ifBlank { url }
            saveSession(url, _currentTitle.value)
        }
    }

    fun handleVoiceCommand(spokenText: String) {
        val lower = spokenText.lowercase().trim()
        when {
            lower.startsWith("go to ") -> {
                val site = lower.removePrefix("go to ").trim()
                loadUrl(site)
            }
            lower.startsWith("search ") -> {
                val query = lower.removePrefix("search ").trim()
                loadUrl("https://www.google.com/search?q=$query")
            }
            lower.contains("home") -> loadUrl("https://www.google.com")
            lower.contains("news") -> loadUrl("https://news.google.com")
            lower.contains("youtube") -> loadUrl("https://www.youtube.com")
            else -> {
                loadUrl(spokenText)
            }
        }
    }
}
