package com.sortabletodolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sortabletodolist.data.dataObjects.TaskDataObject
import com.sortabletodolist.data.dataObjects.abstractions.TaskDao

@Database(entities = [TaskDataObject::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun taskDao(): TaskDao
}
