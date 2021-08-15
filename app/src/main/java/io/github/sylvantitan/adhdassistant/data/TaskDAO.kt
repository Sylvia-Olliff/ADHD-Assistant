package io.github.sylvantitan.adhdassistant.data

import androidx.room.*
import io.github.sylvantitan.adhdassistant.data.models.TaskEntity
import io.github.sylvantitan.adhdassistant.data.models.TaskModel
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TaskDAO {
    @Transaction
    @Query("SELECT * FROM tasks WHERE task_id = :targetUID")
    suspend fun loadTaskByID(targetUID: Int): TaskModel

    @Transaction
    @Query("SELECT * FROM tasks WHERE `group` = :targetGroup")
    suspend fun loadTasksByGroup(targetGroup: String): List<TaskModel>

//    @Transaction
//    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false'")
//    suspend fun loadUncompletedActiveTasks(): Flow<List<TaskEntity>>
//
//    @Transaction
//    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true'")
//    suspend fun loadCompletedActiveTasks(): Flow<List<TaskEntity>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true' AND date_completed < :beforeDate")
    suspend fun loadCompletedActiveTasksSince(beforeDate: Date): List<TaskEntity>

    @Transaction
    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false' AND date_created > :afterDate")
    suspend fun loadUncompletedActiveTasksSince(afterDate: Date): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg tasks: TaskEntity)

    @Update
    suspend fun updateTasks(vararg tasks: TaskEntity)

    @Delete
    suspend fun deleteTasks(vararg tasks: TaskEntity)
}