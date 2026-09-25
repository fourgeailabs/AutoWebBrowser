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
    var whatsNewExpanded by remember { mutableStateOf(false) }

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

            // What's New Section (Collapsible drop-down starting closed)
            item {
                Text(
                    text = "What's New & Updates",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { whatsNewExpanded = !whatsNewExpanded },
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
                            Icon(Icons.Default.NewReleases, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Version 1.01.00 - Release Notes", style = MaterialTheme.typography.titleMedium)
                                Text("Tap to view latest updates and change history", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Icon(
                                if (whatsNewExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = "Expand"
                            )
                        }

                        AnimatedVisibility(visible = whatsNewExpanded) {
                            Column(modifier = Modifier.padding(top = 12.dp)) {
                                Divider()
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("• Version 1.01.00:", style = MaterialTheme.typography.titleSmall)
                                Text("  - Enhanced automatic state persistence: saves and restores active browser tab/page when backgrounded, closed, or killed by the system.", style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("• Version 1.00.00 (Historical):", style = MaterialTheme.typography.titleSmall)
                                Text("  - Initial release of AutoWeb Browser for Android & Dashboards.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Chromium WebView integration with full hardware video acceleration.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Room DB-backed offline caching and bookmark management.", style = MaterialTheme.typography.bodyMedium)
                                Text("  - Voice command speech recognition interface for safe driving.", style = MaterialTheme.typography.bodyMedium)
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
                        Text("AutoWeb Browser v1.01.00", style = MaterialTheme.typography.titleMedium)
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
