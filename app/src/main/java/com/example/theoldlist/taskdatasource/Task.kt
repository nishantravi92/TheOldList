package com.example.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.room.*

@Entity
data class Task(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "due_date") val dueDate: String? = null,
    @ColumnInfo(name = "starred") val starred: Boolean = false
)

@Dao
interface TasksDao {

    @Query("select * from task order by id desc")
    fun getAllTasks(): PagingSource<Int, Task>

    @Query("select count(*) from task")
    fun getTasksCount(): Int

    @Query("select count(*) from task where starred")
    fun getTasksStarredCount(): Int

    // TODO(This needs to be updated. Maybe update the schema to account for date calculations.)
    @Query("select count(*) from task")
    fun getTasksCountByDate(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}

