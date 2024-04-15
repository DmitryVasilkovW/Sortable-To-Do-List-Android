package com.sortabletodolist.sortabletodolistforandroid.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TasksViewModel : ViewModel()
{
    private val _text = MutableLiveData<String>().apply { value = "Tasks" }
    val text: LiveData<String> = _text
}