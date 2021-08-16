package io.github.sylvantitan.adhdassistant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.github.sylvantitan.adhdassistant.data.models.GoalEntity

@Dao
interface GoalDAO {
    @Query("SELECT * FROM goals WHERE goal_id = :targetId")
    suspend fun loadGoalByID(targetId: Int): GoalEntity

    @Query("SELECT * FROM goals WHERE goal_task_id = :targetTaskId")
    suspend fun loadGoalsByTaskID(targetTaskId: Int): List<GoalEntity>

    @Query("SELECT * FROM goals WHERE is_completed = 'false' AND goal_task_id = :targetTaskId")
    fun loadIncompleteGoalsByTaskID(targetTaskId: Int): LiveData<List<GoalEntity>>

    @Query("SELECT COUNT(*) FROM goals WHERE goal_task_id = :targetTaskId")
    suspend fun getNumberOfGoalsByTaskId(targetTaskId: Int): Int

    @Query("SELECT COUNT(*) FROM goals WHERE goal_task_id = :targetTaskId AND is_completed = 'false'")
    suspend fun getNumberOfIncompleteGoalsByTaskId(targetTaskId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(vararg alerts: GoalEntity)

    @Update
    suspend fun updateGoals(vararg alerts: GoalEntity)

    @Delete
    suspend fun deleteGoals(vararg alerts: GoalEntity)
}