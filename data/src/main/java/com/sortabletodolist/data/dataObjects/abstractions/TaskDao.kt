package com.sortabletodolist.data.dataObjects.abstractions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sortabletodolist.data.dataObjects.TaskDataObject

@Dao
interface TaskDao
{
    @Query("SELECT * FROM taskdataobject")
    fun getAll(): List<TaskDataObject>
    @Insert
    fun insert(task: TaskDataObject)
}