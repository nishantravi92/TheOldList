package com.example.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.room.*
import com.example.theoldlist.homelistsdatasource.EntryType
import java.sql.Date
import java.util.*

@Entity
data class Task(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "due_date") val dueDate: Date? = null,
    @ColumnInfo(name = "starred") val starred: Boolean = false,
    @ColumnInfo(name = "task_list_entry_type") val entryType: EntryType = EntryType.HOME,
)

@Dao
interface TasksDao {

    @Query("select * from task order by id desc")
    fun getAllTasks(): PagingSource<Int, Task>

    @Query("select * from task where due_date is not null and (:date >= due_date or cast(:date as text) == cast(due_date as text))")
    fun getTasksByDate(date: Date): PagingSource<Int, Task>

    @Query("select count(*) from task")
    fun getTasksCount(): Int

    @Query("select count(*) from task where starred")
    fun getTasksStarredCount(): Int

    @Query("select * from task where starred order by id desc ")
    fun getAllStarredTasks(): PagingSource<Int, Task>

    @Query("select * from task where task_list_entry_type = :entryType order by id desc ")
    fun getTasksByEntryType(entryType: EntryType): PagingSource<Int, Task>

    @Query("select count(*) from task where task_list_entry_type = :entryType order by id desc ")
    fun getTasksCountByEntryTypeCount(entryType: EntryType): Int

    @Query("select count(*) from task where due_date is not null and :date >= due_date")
    fun getTasksCountByDate(date: Date = Date(Calendar.getInstance().time.time)): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("select * from task where id = :id")
    fun getTask(id: String): Task

    @Delete
    suspend fun deleteTask(task: Task)
}

