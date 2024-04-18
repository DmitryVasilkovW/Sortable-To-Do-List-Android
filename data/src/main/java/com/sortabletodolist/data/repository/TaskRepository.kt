package com.sortabletodolist.data.repository

import android.content.Context
import androidx.room.Room
import com.sortabletodolist.data.database.AppDatabase
import com.sortabletodolist.data.mappers.TaskMapper
import com.sortabletodolist.domain.abstractions.ITaskRepository
import com.sortabletodolist.domain.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(context: Context) : ITaskRepository
{
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "todo_list_database"
    ).build()

    private val taskMapper = TaskMapper()

    suspend override fun addTask(task: Task)
    {
        withContext(Dispatchers.IO)
        {
            db.taskDao().insert(taskMapper.fromTaskToTaskDataObject(task))
        }
    }

    suspend override fun getAllTasks(): List<Task>
    {
        return withContext(Dispatchers.IO)
        {
            db.taskDao().getAll().map { taskDataObject ->
                taskMapper.fromTaskDataObjectToTask(taskDataObject)
            }
        }
    }

    suspend override fun deleteTask(task: Task)
    {
        withContext(Dispatchers.IO)
        {
            db.taskDao().delete(task.id.toString())
        }
    }

    suspend override fun saveTask(task: Task)
    {
        if (task.id != null && db.taskDao().get(task.id.toString()) != null)
        {
            db.taskDao().update(taskMapper.fromTaskToTaskDataObject(task))
        }
        else
        {
            db.taskDao().insert(taskMapper.fromTaskToTaskDataObject(task))
        }
    }
}