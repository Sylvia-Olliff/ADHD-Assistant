package io.github.sylvantitan.adhdassistant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckinEntity(
    @ColumnInfo(name = "minutes") val minutes: Int,
    @ColumnInfo(name = "seconds") val seconds: Int,
    @ColumnInfo(name = "repeats") val repeats: Boolean,
    @ColumnInfo(name = "message") val message: String?,
    /**
     * rate of increasing/decreasing the interval between checkins until task marked completed
     * between 0 - 1 = decreasing interval, 1+ increasing interval
     */
    @ColumnInfo(name = "scale", defaultValue = "1.0") val scale: Float
)
