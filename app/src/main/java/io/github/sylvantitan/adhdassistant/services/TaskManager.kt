package io.github.sylvantitan.adhdassistant.services

import android.content.Context
import io.github.sylvantitan.adhdassistant.data.DB

class TaskManager {
    lateinit var db: DB

    fun init(context: Context) {
        db = DB.getInstance(context)
    }
}