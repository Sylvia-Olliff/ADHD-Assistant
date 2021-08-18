package io.github.sylvantitan.adhdassistant.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.github.sylvantitan.adhdassistant.ui.composables.TaskCard
import io.github.sylvantitan.adhdassistant.ui.composables.mockTaskModel
import io.github.sylvantitan.adhdassistant.ui.theme.ADHDAssistantTheme

@AndroidEntryPoint
class TasksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADHDAssistantTheme {
                TaskCard(task = mockTaskModel)
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
        TaskCard(mockTaskModel)
    }
}