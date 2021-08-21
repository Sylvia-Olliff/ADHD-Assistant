package io.github.sylvantitan.adhdassistant.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sylvantitan.adhdassistant.data.models.*
import io.github.sylvantitan.adhdassistant.ui.utils.toFormattedString
import java.time.LocalDateTime

@Composable
fun TaskCard(task: TaskModel) {
    Card(
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(Modifier.fillMaxWidth(0.9f)) {
            Row(Modifier.padding(all = 8.dp)) {
                TaskCardHeader(task = task.task)
            }
            Row(Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 6.dp)) {
                TaskCardDetails(task = task.task)
            }
            Row(Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 6.dp)) {
                TaskCardBody(task)
            }
        }
    }
//    Column(modifier =
//    Modifier
//        .padding(2.dp)
//        .fillMaxWidth()
//    ) {
//        Row(Modifier.padding(all = 8.dp)) {
//            TaskCardHeader(task = task.task)
//        }
//        Row(Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 6.dp)) {
//            TaskCardDetails(task = task.task)
//        }
//        Row(Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 6.dp)) {
//            TaskCardBody(task)
//        }
//    }
}

@Composable
fun TaskCardHeader(task: TaskEntity) {
    Text(
        text = task.name,
        color = MaterialTheme.colors.primaryVariant,
        style = MaterialTheme.typography.h6
    )
    Spacer(Modifier.width(4.dp))

    Column(Modifier.padding(all = 4.dp)) {
        TaskCardText("Group: ${task.group}")
        Spacer(Modifier.height(2.dp))
        TaskCardText("Enabled: ${if (task.enabled) { "Yes" } else { "No" } }")
    }
    Spacer(Modifier.width(4.dp))

    Column(Modifier.padding(all = 4.dp)) {
        TaskCardText("Created: ${task.dateCreated.toFormattedString()}")
        Spacer(Modifier.height(2.dp))
        TaskCardText("Completed: ${if (task.completed && task.dateCompleted != null) { task.dateCompleted.toFormattedString() } else { "No" } }")
    }
}

@Composable
fun TaskCardDetails(task: TaskEntity) {
    if (task.checkin != null) {
        Column(Modifier.padding(all = 4.dp)) {
            TaskCardText("Checkin: Enabled")
            Spacer(Modifier.height(2.dp))
            TaskCardText("Checkin Interval (m:s) : ${task.checkin.minutes}:${task.checkin.seconds}")
            if (task.checkin.repeats) {
                Spacer(Modifier.height(2.dp))
                TaskCardText("Repeats on a scale of ${task.checkin.scale}")
            } else {
                Spacer(Modifier.height(4.dp))
            }
        }
        Spacer(Modifier.width(4.dp))
        Column(Modifier.padding(all = 4.dp)) {
            TaskCardText("Checkin Message: ${task.checkin.message}")
        }
    } else {
        Column(Modifier.padding(all = 4.dp)) {
            TaskCardText("Checkin: Disabled")
            Spacer(Modifier.height(6.dp))
        }
        Spacer(Modifier.width(4.dp))
        Column(Modifier.padding(all = 4.dp)) {
            Spacer(Modifier.height(6.dp))
        }
    }
}

@Composable
fun TaskCardBody(task: TaskModel) {
    Column(
        Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
    ) {
        TaskCardText("Description: ${task.task.description ?: "N/A"}")
        Spacer(Modifier.height(3.dp))

        Row(
            Modifier
                .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 2.dp)
                .fillMaxWidth()
        ) {
            TaskCardTextHeader("-- GOALS --")
        }
        TaskCardGoalList(task.goals)

        Row(
            Modifier
                .padding(start = 2.dp, end = 2.dp, top = 4.dp, bottom = 2.dp)
                .fillMaxWidth()
        ) {
            TaskCardTextHeader("-- ALERTS --")
        }
        TaskCardAlertList(task.alerts)
    }
}

@Composable
fun TaskCardGoalList(goals: List<GoalEntity>) {
    var listCount = 0
    LazyColumn(contentPadding = PaddingValues(horizontal = 2.dp, vertical = 6.dp)) {
        items(goals) { goal ->
            listCount++
            TaskCardGoalRow(goal, listCount)
        }
        listCount = 0
    }
}

@Composable
fun TaskCardAlertList(alerts: List<AlertEntity>) {

}

@Composable
fun TaskCardGoalRow(goal: GoalEntity, goalCount: Int) {
    Row(
        Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()) {
        Column(Modifier.weight(1f, true)) {
            TaskCardTextDetails("${goalCount}.  ${goal.name}")
        }
        Spacer(Modifier.width(3.dp))
        Column(Modifier.weight(0.5f, true)) {
            TaskCardTextDetails(if (goal.completed) { "complete" } else { "incomplete" })
        }
        Column(Modifier.weight(0.5f, true)) {
            TaskCardTextDetails("${goal.points} pts")
        }
    }
}

@Composable
fun TaskCardText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle2
    )
}

@Composable
fun TaskCardTextBody(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.secondary,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TaskCardTextDetails(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun TaskCardTextHeader(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.secondary,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
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
    goals = listOf(
        GoalEntity(
            goalId = 3,
            goalTaskId = 5,
            completed = false,
            name = "Hug Christine",
            description = "Christine is amazing and deserves snuggles, go hug her!!",
            points = 50
        ),
        GoalEntity(
            goalId = 4,
            goalTaskId = 5,
            completed = false,
            name = "Hug Sarah",
            description = "Sarah is nice too, she should get a hug as well!!",
            points = 25
        ),
        GoalEntity(
            goalId = 5,
            goalTaskId = 5,
            completed = true,
            name = "Hug Yourself",
            description = "Don't forget to hug yourself! You deserve it!!",
            points = 75
        )
    ),
    alerts = listOf(AlertEntity(
        alertId = 2,
        alertTaskId = 5,
        alertTime = LocalDateTime.now().plusHours(2),
        name = "Hug Christine Time!",
        active = true,
        configName = "default"
    ))
)