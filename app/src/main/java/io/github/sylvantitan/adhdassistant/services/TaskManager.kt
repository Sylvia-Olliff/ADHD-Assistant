package io.github.sylvantitan.adhdassistant.services

import android.content.Context
import io.github.sylvantitan.adhdassistant.data.AlertDAO
import io.github.sylvantitan.adhdassistant.data.DB
import io.github.sylvantitan.adhdassistant.data.GoalDAO
import io.github.sylvantitan.adhdassistant.data.TaskDAO
import io.github.sylvantitan.adhdassistant.data.models.AlertEntity
import io.github.sylvantitan.adhdassistant.data.models.GoalEntity
import io.github.sylvantitan.adhdassistant.data.models.TaskEntity
import io.github.sylvantitan.adhdassistant.data.models.TaskModel
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class TaskManager {
    companion object {
        lateinit var db: DB
        lateinit var taskDAO: TaskDAO
        lateinit var alertDAO: AlertDAO
        lateinit var goalDAO: GoalDAO

        fun init(context: Context) {
            db = DB.getInstance(context)

            taskDAO = db.taskDAO()
            alertDAO = db.alertDAO()
            goalDAO = db.goalDAO()
        }

        fun observeAllTasksByGroup(group: String): Flow<List<TaskEntity>> {
            return taskDAO.loadTasksByGroup(group)
        }

        fun observeTasks(group: String?, completed: Boolean = false): Flow<List<TaskEntity>> {
            return if (group != null) {
                if (completed) {
                   taskDAO.loadCompletedActiveTasksByGroup(group)
                } else {
                   taskDAO.loadIncompleteActiveTasksByGroup(group)
                }
            } else {
                if (completed) {
                    taskDAO.loadCompletedActiveTasks()
                } else {
                    taskDAO.loadIncompleteActiveTasks()
                }
            }
        }

        fun observeTasksCount(group: String?, completed: Boolean = false): Flow<Int> {
            return if (group != null) {
                if (completed) {
                    taskDAO.loadCompletedTasksCountByGroup(group)
                } else {
                    taskDAO.loadIncompleteTasksCountByGroup(group)
                }
            } else {
                if (completed) {
                    taskDAO.loadCompletedTasksCount()
                } else {
                    taskDAO.loadIncompleteTasksCount()
                }
            }
        }

        suspend fun loadTaskModel(taskEntity: TaskEntity): TaskModel {
            return taskDAO.loadTaskByID(taskEntity.taskId)
        }

        suspend fun addNewTasks(tasks: Array<TaskModel>): Boolean {
            return try {
                val taskEntities = tasks.map { it.task }.toTypedArray()
                val goalSetList: MutableList<GoalEntity> = mutableListOf()
                val alertSetList: MutableList<AlertEntity> = mutableListOf()
                tasks.forEach {
                    goalSetList.addAll(it.goals)
                    alertSetList.addAll(it.alerts)
                }
                val goalSet = goalSetList.toTypedArray()
                val alertSet = alertSetList.toTypedArray()

                taskDAO.insertTasks(*taskEntities)
                goalDAO.insertGoals(*goalSet)
                alertDAO.insertAlerts(*alertSet)
                true
            } catch (error: Exception) {
                // TODO: Handle error/log error in some way
                false
            }
        }

//        suspend fun updateTasks(tasks: Array<TaskEn>)
    }
}