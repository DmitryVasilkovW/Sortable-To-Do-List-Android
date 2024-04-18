package com.sortabletodolist.domain.abstractions

import com.sortabletodolist.domain.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ITaskRepository
{
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun deleteTask(task: Task)
    suspend fun saveTask(task: Task)

}