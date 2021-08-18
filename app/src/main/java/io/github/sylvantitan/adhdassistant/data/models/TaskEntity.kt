package io.github.sylvantitan.adhdassistant.data.models

import androidx.room.*
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey @ColumnInfo(name = "task_id") val taskId: Int,
    @ColumnInfo(name = "date_created") val dateCreated: LocalDateTime,
    @ColumnInfo(name = "date_completed") val dateCompleted: LocalDateTime?,
    @ColumnInfo(name = "group") val group: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_completed", defaultValue = "false") val completed: Boolean,
    @ColumnInfo(name = "is_enabled", defaultValue = "true") val enabled: Boolean,
    @ColumnInfo(name = "description") val description: String?,
    @Embedded val checkin: CheckinEntity?,
)
