package io.github.sylvantitan.adhdassistant.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.github.sylvantitan.adhdassistant.ui.composables.TaskCard
import io.github.sylvantitan.adhdassistant.ui.composables.mockTaskModel
import io.github.sylvantitan.adhdassistant.ui.theme.ADHDAssistantTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADHDAssistantTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = { Text("Drawer content") }, // TODO: Replace with composable
                    topBar = { // TODO: Replace with composable
                        TopAppBar(
                            title = { Text("Task View") },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        scope.launch { scaffoldState.drawerState.open() }
                                    }
                                ) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                                }
                            }
                        )
                    },
                    backgroundColor = MaterialTheme.colors.background,
                    drawerBackgroundColor = MaterialTheme.colors.onBackground,
                ) {
                    Column(Modifier.padding(it).fillMaxWidth()) {
                        TaskCard(task = mockTaskModel)
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode"
)
@Composable
fun TaskCardPreview() {
    ADHDAssistantTheme {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = { Text("Drawer content") }, // TODO: Replace with composable
            topBar = { // TODO: Replace with composable
                TopAppBar(
                    title = { Text("Task View") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch { scaffoldState.drawerState.open() }
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                        }
                    }
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            drawerBackgroundColor = MaterialTheme.colors.onBackground,
        ) {
            Column(Modifier.padding(it).fillMaxWidth()) {
                TaskCard(task = mockTaskModel)
            }
        }
    }
}