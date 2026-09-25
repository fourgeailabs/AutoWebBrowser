package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: BrowserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by viewModel.isDarkMode.collectAsState()
            val bookmarks by viewModel.bookmarks.collectAsState()
            val cachedArticles by viewModel.cachedArticles.collectAsState()
            val navController = rememberNavController()

            MyApplicationTheme(darkTheme = isDarkMode) {
                NavHost(navController = navController, startDestination = "browser") {
                    composable("browser") {
                        BrowserScreen(
                            viewModel = viewModel,
                            onNavigateBookmarks = { navController.navigate("bookmarks") },
                            onNavigateOffline = { navController.navigate("offline") },
                            onNavigateSettings = { navController.navigate("settings") }
                        )
                    }
                    composable("bookmarks") {
                        BookmarksScreen(
                            bookmarks = bookmarks,
                            onBookmarkClick = { url ->
                                viewModel.loadUrl(url)
                                navController.popBackStack()
                            },
                            onDeleteBookmark = { id -> viewModel.deleteBookmark(id) },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("offline") {
                        OfflineCacheScreen(
                            cachedArticles = cachedArticles,
                            onItemClick = { url ->
                                viewModel.loadUrl(url)
                                navController.popBackStack()
                            },
                            onDeleteItem = { id -> viewModel.deleteCachedArticle(id) },
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("settings") {
                        SettingsScreen(
                            isDarkMode = isDarkMode,
                            onToggleDarkMode = { viewModel.toggleDarkMode() },
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveCurrentSession(viewModel.currentUrl.value, viewModel.currentTitle.value)
    }
}
