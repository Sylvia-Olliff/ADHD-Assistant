package io.github.sylvantitan.adhdassistant.data.models

data class TaskModel(
    val task: TaskEntity,
    val goals: MutableList<GoalEntity>,
    val alerts: MutableList<AlertEntity>,
    val checkIns: MutableList<CheckinEntity>
)
