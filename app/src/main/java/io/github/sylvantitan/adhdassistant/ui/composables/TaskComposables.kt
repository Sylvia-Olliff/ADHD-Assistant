package io.github.sylvantitan.adhdassistant.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sylvantitan.adhdassistant.data.models.*
import io.github.sylvantitan.adhdassistant.ui.utils.toFormattedString
import java.time.LocalDateTime

@Composable
fun TaskCard(task: TaskModel) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        TaskCardHeader(task = task.task)
    }
}

@Composable
fun TaskCardHeader(task: TaskEntity) {
    Text(
        text = task.name,
        color = MaterialTheme.colors.primaryVariant,
        style = MaterialTheme.typography.h6
    )
    Spacer(modifier = Modifier.width(4.dp))

    Column(modifier = Modifier.padding(all = 4.dp)) {
        Text(
            text = "Group: ${task.group}",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Enabled: ${if (task.enabled) { "Yes" } else { "No" } }",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
    }
    Spacer(modifier = Modifier.width(4.dp))

    Column(modifier = Modifier.padding(all = 4.dp)) {
        Text(
            text = "Created: ${task.dateCreated.toFormattedString()}",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Completed: ${if (task.completed && task.dateCompleted != null) { task.dateCompleted.toFormattedString() } else { "No" } }",
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

val mockTaskModel = TaskModel(
    task = TaskEntity(
        taskId = 5,
        dateCreated = LocalDateTime.now(),
        dateCompleted = null,
        group = "Test",
        name = "Example",
        completed = false,
        enabled = true,
        description = "This is an example task for preview",
        checkin = CheckinEntity(
            minutes = 5,
            seconds = 30,
            repeats = true,
            message = "Just checking in! <3 have you started the task?",
            scale = 1.0f
        )
    ),
    goals = listOf(GoalEntity(
        goalId = 3,
        goalTaskId = 5,
        completed = false,
        name = "Hug Christine",
        description = "Christine is amazing and deserves snuggles, go hug her!!",
        points = 50
    )),
    alerts = listOf(AlertEntity(
        alertId = 2,
        alertTaskId = 5,
        alertTime = LocalDateTime.now().plusHours(2),
        name = "Hug Christine Time!",
        active = true,
        configName = "default"
    ))
)