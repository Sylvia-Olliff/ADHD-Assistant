package io.github.sylvantitan.adhdassistant.data

import androidx.lifecycle.LiveData
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

    @Query("SELECT * FROM tasks WHERE `group` = :targetGroup")
    fun loadTasksByGroup(targetGroup: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false'")
    fun loadIncompleteActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false' AND `group` = :group")
    fun loadIncompleteActiveTasksByGroup(group: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true'")
    fun loadCompletedActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true' AND `group` = :group")
    fun loadCompletedActiveTasksByGroup(group: String): Flow<List<TaskEntity>>

    @Query("SELECT COUNT(*) FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false'")
    fun loadIncompleteTasksCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM tasks WHERE is_enabled = 'true' AND is_completed = 'false' AND `group` = :group")
    fun loadIncompleteTasksCountByGroup(group: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true'")
    fun loadCompletedTasksCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM tasks WHERE is_enabled = 'true' AND is_completed = 'true' AND `group` = :group")
    fun loadCompletedTasksCountByGroup(group: String): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(vararg tasks: TaskEntity)

    @Update
    suspend fun updateTasks(vararg tasks: TaskEntity)

    @Delete
    suspend fun deleteTasks(vararg tasks: TaskEntity)
}