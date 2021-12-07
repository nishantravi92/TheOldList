package com.example.theoldlist.homelistsdatasource

import androidx.paging.PagingSource
import androidx.room.*

enum class EntryType {
    HOME,
    STARRED,
    TODAY,
    WEEK,
    MOVIES,
    BOOKS
}

@Entity(tableName = "homelistentry")
data class HomeListEntry(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "entry_type") val entryType: EntryType,
)

@Dao
interface HomeListEntryDao {

    @Query("select * from homelistentry")
    fun getAllEntriesAsPagingSource(): PagingSource<Int, HomeListEntry>

    @Query("select * from homelistentry")
    fun getAllEntries(): List<HomeListEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListEntry(listEntry: HomeListEntry)

    @Delete
    suspend fun deleteEntry(listEntry: HomeListEntry)
}
