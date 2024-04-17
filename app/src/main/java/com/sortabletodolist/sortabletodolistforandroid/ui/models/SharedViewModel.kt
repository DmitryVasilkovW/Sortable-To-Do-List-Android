package com.sortabletodolist.sortabletodolistforandroid.ui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sortabletodolist.domain.models.Task

class SharedViewModel : ViewModel()
{
    val tasks = MutableLiveData<List<Task>>()

    fun updateTasks(newTasks: List<Task>)
    {
        tasks.value = newTasks
    }
}