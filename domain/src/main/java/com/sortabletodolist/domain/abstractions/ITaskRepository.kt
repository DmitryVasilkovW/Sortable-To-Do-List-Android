package com.sortabletodolist.domain.abstractions

import com.sortabletodolist.domain.models.Task

interface ITaskRepository
{
    suspend fun saveTask(task: Task)
    suspend fun getAllTasks(): List<Task>
}