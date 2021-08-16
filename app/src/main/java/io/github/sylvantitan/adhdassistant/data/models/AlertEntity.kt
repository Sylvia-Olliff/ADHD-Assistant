package io.github.sylvantitan.adhdassistant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "alerts")
data class AlertEntity(
    @PrimaryKey @ColumnInfo(name = "alert_id") val alertId: Int,
    @ColumnInfo(name = "alert_task_id") val alertTaskId: Int,
    @ColumnInfo(name = "alert_time") val alertTime: Date,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_active") val active: Boolean,
    @ColumnInfo(name = "config", defaultValue = "DEFAULT") val configName: String
    // TODO: Convert Alert 'config' column to a config reference
    // TODO: Add Converter for String -> AlertConfig and vice versa
    // TODO: Add AlertConfig Enum
)
