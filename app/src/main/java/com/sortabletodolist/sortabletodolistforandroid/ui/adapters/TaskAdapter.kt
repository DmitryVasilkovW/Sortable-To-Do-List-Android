package com.sortabletodolist.sortabletodolistforandroid.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.sortabletodolistforandroid.R

class TaskAdapter(private val tasks: List<Task>) : BaseAdapter()
{

    override fun getCount(): Int
    {
        return tasks.size
    }

    override fun getItem(position: Int): Any
    {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val view: View
        if (convertView == null)
        {
            view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        }
        else
        {
            view = convertView
        }

        val task = getItem(position) as Task
        val taskNameTextView = view.findViewById<TextView>(R.id.taskNameTextView)
        val taskTextTextView = view.findViewById<TextView>(R.id.taskTextTextView)
        val taskCompleateStatusView = view.findViewById<CheckBox>(R.id.taskStatusCheckBox)

        taskNameTextView.text = task.name
        taskTextTextView.text = task.text
        taskCompleateStatusView.isChecked = task.isCompleted
        taskCompleateStatusView.isClickable = false
        taskCompleateStatusView.isFocusable = false

        return view
    }

}
