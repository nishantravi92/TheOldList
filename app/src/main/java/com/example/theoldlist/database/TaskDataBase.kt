package com.example.theoldlist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.theoldlist.homelistsdatasource.HomeListEntry
import com.example.theoldlist.homelistsdatasource.HomeListEntryDao
import com.example.theoldlist.taskdatasource.Task
import com.example.theoldlist.taskdatasource.TasksDao

@Database(entities = [Task::class, HomeListEntry::class], version = 1)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun tasksDao(): TasksDao
    abstract fun homeListEntryDao(): HomeListEntryDao
}