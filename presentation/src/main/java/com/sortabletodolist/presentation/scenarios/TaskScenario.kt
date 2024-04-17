package com.sortabletodolist.presentation.scenarios

import android.content.Context
import com.sortabletodolist.data.repository.TaskRepository
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.interactors.TaskInteractor

class TaskScenario
{
    suspend fun addTask(id: Int?, taskName: String, taskText: String, isCompleted: Boolean, context: Context)
    {
        val taskRepository = TaskRepository(context)
        val taskInteractor = TaskInteractor(taskRepository)

        taskInteractor.addTask(Task(id, taskName, taskText, isCompleted))
    }

    suspend fun getAllTasks(context: Context): List<Task>
    {
        val taskRepository = TaskRepository(context)
        val taskInteractor = TaskInteractor(taskRepository)

        return taskInteractor.getAllTasks()
    }
}