package io.github.sylvantitan.adhdassistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.sylvantitan.adhdassistant.consts.DBStrings
import io.github.sylvantitan.adhdassistant.data.models.AlertEntity
import io.github.sylvantitan.adhdassistant.data.models.GoalEntity
import io.github.sylvantitan.adhdassistant.data.utils.Converters
import io.github.sylvantitan.adhdassistant.data.models.TaskEntity

@Database(entities = [TaskEntity::class, AlertEntity::class, GoalEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO

    companion object {
        @Volatile private var instance: DB? = null

        fun getInstance(context: Context): DB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): DB {
            return Room.databaseBuilder(context, DB::class.java, DBStrings.DATABASE_NAME)
                .build()
        }
    }
}