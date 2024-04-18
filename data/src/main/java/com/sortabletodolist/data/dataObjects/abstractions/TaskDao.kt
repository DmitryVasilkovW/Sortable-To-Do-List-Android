package com.sortabletodolist.data.dataObjects.abstractions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sortabletodolist.data.dataObjects.TaskDataObject

@Dao
interface TaskDao
{
    @Query("SELECT * FROM taskdataobject")
    fun getAll(): List<TaskDataObject>
    @Query("SELECT * FROM taskdataobject WHERE id = :taskId")
    fun get(taskId: String): TaskDataObject?
    @Insert
    fun insert(task: TaskDataObject)
    @Update
    fun update(task: TaskDataObject)
    @Query("DELETE FROM taskdataobject WHERE id = :taskId")
    fun delete(taskId: String)
}