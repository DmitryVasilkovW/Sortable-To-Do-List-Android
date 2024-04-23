package com.sortabletodolist.sortabletodolistforandroid.ui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.presentation.scenarios.TaskScenario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel()
{
    val tasks = MutableLiveData<List<Task>>()

    fun updateTasks(newTasks: List<Task>)
    {
        tasks.postValue(newTasks)
    }

    fun updateTask(updatedTask: Task, scenario: TaskScenario)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            scenario.saveTask(updatedTask)
            launch(Dispatchers.Main)
            {
                updateTasks(scenario.getAllTasks())
            }
        }
    }

    fun deleteTask(task: Task, scenario: TaskScenario)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            scenario.deleteTask(task)
            launch(Dispatchers.Main)
            {
                updateTasks(scenario.getAllTasks())
            }
        }
    }
}