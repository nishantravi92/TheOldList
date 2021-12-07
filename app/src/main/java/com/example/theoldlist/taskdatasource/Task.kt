package com.example.theoldlist.taskdatasource

import androidx.paging.PagingSource
import androidx.room.*
import com.example.theoldlist.homelistsdatasource.EntryType
import com.example.theoldlist.homepage.ListEntryType
import java.sql.Date

@Entity
data class Task(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "due_date") val dueDate: Date? = null,
    @ColumnInfo(name = "starred") val starred: Boolean = false,
    @ColumnInfo(name = "task_list_entry_type") val entryType: EntryType = EntryType.HOME,
)

@Dao
interface TasksDao {

    @Query("select * from task order by id desc")
    fun getAllTasks(): PagingSource<Int, Task>

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

    // TODO(This needs to be updated. Maybe update the schema to account for date calculations.)
    @Query("select count(*) from task")
    fun getTasksCountByDate(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}

