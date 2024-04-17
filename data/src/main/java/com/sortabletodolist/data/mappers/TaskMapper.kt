package com.sortabletodolist.data.mappers

import com.sortabletodolist.data.dataObjects.TaskDataObject
import com.sortabletodolist.domain.models.Task

class TaskMapper()
{
    fun fromTaskDataObjectToTask(taskDataObject: TaskDataObject): Task
    {
        return Task(taskDataObject.id, taskDataObject.name, taskDataObject.text, taskDataObject.isCompleted)
    }

    fun fromTaskToTaskDataObject(task: Task): TaskDataObject
    {
        return TaskDataObject(task.id, task.name, task.text, task.isCompleted)
    }
}