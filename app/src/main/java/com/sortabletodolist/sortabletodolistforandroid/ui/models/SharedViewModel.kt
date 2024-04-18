package com.sortabletodolist.sortabletodolistforandroid.ui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.presentation.scenarios.TaskScenario

class SharedViewModel : ViewModel()
{
    val tasks = MutableLiveData<List<Task>>()

    fun updateTasks(newTasks: List<Task>)
    {
        tasks.postValue(newTasks)
    }

    suspend fun updateTask(updatedTask: Task, scenario: TaskScenario)
    {
        scenario.saveTask(updatedTask)
        updateTasks(scenario.getAllTasks())
    }

    suspend fun deleteTask(task: Task, scenario: TaskScenario)
    {
        scenario.deleteTask(task)
        updateTasks(scenario.getAllTasks())
    }
}