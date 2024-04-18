package com.sortabletodolist.presentation.scenarios

import android.content.Context
import com.sortabletodolist.data.repository.TaskRepository
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.interactors.TaskInteractor

class TaskScenario(private val context: Context)
{
    private val taskRepository = TaskRepository(context)
    private val taskInteractor = TaskInteractor(taskRepository)

    suspend fun addTask(id: Int?, taskName: String, taskText: String, isCompleted: Boolean)
    {
        taskInteractor.addTask(Task(id, taskName, taskText, isCompleted))
    }

    suspend fun getAllTasks(): List<Task>
    {
        return taskInteractor.getAllTasks()
    }

    suspend fun deleteTask(task: Task)
    {
        taskInteractor.deleteTask(task)
    }

    suspend fun saveTask(task: Task)
    {
        taskInteractor.saveTask(task)
    }
}