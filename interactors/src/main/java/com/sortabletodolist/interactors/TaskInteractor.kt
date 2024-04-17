package com.sortabletodolist.interactors

import com.sortabletodolist.domain.abstractions.ITaskRepository
import com.sortabletodolist.domain.models.Task

class TaskInteractor(private val taskRepository: ITaskRepository)
{
    suspend fun addTask(task: Task)
    {
        taskRepository.saveTask(task)
    }

    suspend fun getAllTasks(): List<Task>
    {
        return taskRepository.getAllTasks()
    }
}