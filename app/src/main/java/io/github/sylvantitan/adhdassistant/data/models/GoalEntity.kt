package io.github.sylvantitan.adhdassistant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey @ColumnInfo(name = "goal_id") val goalId: Int,
    @ColumnInfo(name = "goal_task_id") val goalTaskId: Int,
    @ColumnInfo(name = "is_completed", defaultValue = "false") val completed: Boolean,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "value", defaultValue = "10") val points: Int
)
