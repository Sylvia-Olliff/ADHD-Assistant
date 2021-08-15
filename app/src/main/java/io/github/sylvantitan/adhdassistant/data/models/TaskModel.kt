package io.github.sylvantitan.adhdassistant.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TaskModel(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "task_id",
        entityColumn = "alert_task_id"
    )
    val alerts: List<AlertEntity>,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "goal_task_id"
    )
    val goals: List<GoalEntity>
)
