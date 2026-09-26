package com.example.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var openedUpdateVersion by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings & About") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Preferences section
            item {
                Text(
                    text = "Preferences",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DarkMode, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Dark Mode", style = MaterialTheme.typography.bodyLarge)
                            Text("Optimized for night driving & dashboards", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { onToggleDarkMode() },
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }

            // What's New Section (Drop down that starts closed and closes previously opened one)
            item {
                Text(
                    text = "What's New & Release History",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Version 1.02.00 Update Notice
            item {
                val isExpanded = openedUpdateVersion == "1.02.00"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openedUpdateVersion = if (isExpanded) null else "1.02.00"
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.NewReleases, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Version 1.02.00 (Current)", style = MaterialTheme.typography.titleMedium)
                                Text("Android Auto projected car screen support", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(
                                if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand"
                            )
                        }

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("• Full Android Auto Projection Support:", style = MaterialTheme.typography.titleSmall)
                                Text("  - Native Android for Cars App Library (CarAppService & Session) implementation for projected in-vehicle head unit screens.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Safe driving templates optimized for glanceable in-car use with oversized buttons and distraction-free design.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Car quick-portal for hands-free search, live regional weather, audio/news streams, and one-tap device browser launching.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Configured template capability descriptor and host validation for seamless Android Auto recognition.", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // Version 1.01.00 Update Notice
            item {
                val isExpanded = openedUpdateVersion == "1.01.00"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openedUpdateVersion = if (isExpanded) null else "1.01.00"
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.History, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Version 1.01.00", style = MaterialTheme.typography.titleMedium)
                                Text("Session persistence and state recovery", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(
                                if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand"
                            )
                        }

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("• State & Session Persistence:", style = MaterialTheme.typography.titleSmall)
                                Text("  - Saves and restores active browser tab/page when backgrounded, closed, or recreated by the system.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Preserves user reading position and browsing state seamlessly.", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // Version 1.00.00 Update Notice
            item {
                val isExpanded = openedUpdateVersion == "1.00.00"
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openedUpdateVersion = if (isExpanded) null else "1.00.00"
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.History, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Version 1.00.00", style = MaterialTheme.typography.titleMedium)
                                Text("Initial release of AutoWeb Browser", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(
                                if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand"
                            )
                        }

                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                HorizontalDivider()
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("• Initial Platform Launch:", style = MaterialTheme.typography.titleSmall)
                                Text("  - High-performance Chromium WebView browser with hardware acceleration.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Room DB offline caching for reading saved web pages without internet.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Voice command speech recognition for safe hands-free navigation.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Bookmark management and full HTML5 video playback support.", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // About Section
            item {
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("AutoWeb Browser v1.02.00", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "App Creator: FourgeAI Labs",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fourgeailabs"))
                                context.startActivity(intent)
                            }
                        )
                        Text(
                            text = "App Repository: GitHub (fourgeailabs/autowebbrowser)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/fourgeailabs/autowebbrowser"))
                                context.startActivity(intent)
                            }
                        )
                        Text(
                            text = "Licensed under FourgeAI Labs Proprietary Software License Version 1.0.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
