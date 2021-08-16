package io.github.sylvantitan.adhdassistant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.sylvantitan.adhdassistant.data.models.AlertEntity

@Dao
interface AlertDAO {
    @Query("SELECT * FROM alerts WHERE alert_id = :targetId")
    suspend fun loadAlertByID(targetId: Int): AlertEntity

    @Query("SELECT * FROM alerts WHERE alert_task_id = :targetTaskId")
    suspend fun loadAlertsByTaskID(targetTaskId: Int): List<AlertEntity>

    @Query("SELECT * FROM alerts WHERE is_active = 'true' AND alert_task_id = :targetTaskId")
    fun loadAllActiveAlertsByID(targetTaskId: Int): LiveData<List<AlertEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlerts(vararg alerts: AlertEntity)

    @Update
    suspend fun updateAlerts(vararg alerts: AlertEntity)

    @Delete
    suspend fun deleteAlerts(vararg alerts: AlertEntity)
}