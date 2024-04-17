package com.sortabletodolist.data.dataObjects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskDataObject(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val text: String,
    val isCompleted: Boolean
)
