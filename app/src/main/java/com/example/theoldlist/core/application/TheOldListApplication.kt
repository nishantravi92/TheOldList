package com.example.theoldlist.core.application

import android.app.Application
import androidx.room.Room
import com.example.theoldlist.database.TaskDataBase


class TheOldListApplication : Application() {
    lateinit var _dataBase: TaskDataBase

    private val _tasksDao = lazy { _dataBase.tasksDao() }
    private val _homeListDao = lazy { _dataBase.homeListEntryDao() }

    val tasksDao get() = _tasksDao.value
    val homeListEntryDao get() = _homeListDao.value

    override fun onCreate() {
        super.onCreate()
        _dataBase = Room.databaseBuilder(this, TaskDataBase::class.java, "database-name").build()
    }

}