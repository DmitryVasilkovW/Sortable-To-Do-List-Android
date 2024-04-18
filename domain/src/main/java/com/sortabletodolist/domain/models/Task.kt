package com.sortabletodolist.domain.models

import java.io.Serializable

class Task(id: Int?, name: String, text: String, isCompleted: Boolean) : Serializable
{
    var id: Int? = id
        private set
    var name: String = name
        private set
    var text: String = text
        private set
    var isCompleted: Boolean = isCompleted
        private set
}
