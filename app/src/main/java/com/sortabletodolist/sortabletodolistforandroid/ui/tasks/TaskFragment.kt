package com.sortabletodolist.sortabletodolistforandroid.ui.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.presentation.scenarios.TaskScenario
import com.sortabletodolist.sortabletodolistforandroid.R
import com.sortabletodolist.sortabletodolistforandroid.ui.adapters.TaskAdapter
import com.sortabletodolist.sortabletodolistforandroid.ui.dialog.DialogFragment
import com.sortabletodolist.sortabletodolistforandroid.ui.dialog.EditTaskDialogFragment
import com.sortabletodolist.sortabletodolistforandroid.ui.models.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskFragment : Fragment()
{
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var taskScenario: TaskScenario

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        taskScenario = TaskScenario(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val taskCountTextView = view.findViewById<TextView>(R.id.taskCountTextView)
        val taskListView = view.findViewById<ListView>(R.id.taskListView)

        lifecycleScope.launch {
            val tasks = taskScenario.getAllTasks()

            sharedViewModel.updateTasks(tasks)
        }

        sharedViewModel.tasks.observe(viewLifecycleOwner)
        { tasks ->
            val taskAdapter = TaskAdapter(
                tasks
            ) { updatedTask ->
                lifecycleScope.launch(Dispatchers.IO)
                {
                    taskScenario.saveTask(updatedTask)
                    launch(Dispatchers.Main)
                    {
                        sharedViewModel.updateTask(updatedTask, taskScenario)
                    }
                }
            }

            taskListView.adapter = taskAdapter

            taskCountTextView.text = when
            {
                tasks.isEmpty() ->
                {
                    "У вас нет задач"
                }
                tasks.size % 10 == 1 && tasks.size % 100 != 11 ->
                {
                    "У вас ${tasks.size} задача"
                }
                tasks.size % 10 in 2..4 && tasks.size % 100 !in 12..14 ->
                {
                    "У вас ${tasks.size} задачи"
                }
                else ->
                {
                    "У вас ${tasks.size} задач"
                }
            }
        }


        taskListView.setOnItemClickListener { parent, view, position, id ->
            if (view.id != R.id.taskStatusCheckBox)
            {
                val selectedTask = parent.getItemAtPosition(position) as Task
                val editDialogFragment = EditTaskDialogFragment.newInstance(selectedTask)

                editDialogFragment.show(childFragmentManager, "EditTaskDialogFragment")
            }
        }

        fabAddTask = view.findViewById(R.id.fab_add_task)
        fabAddTask.setOnClickListener {

            val inputDialogFragment = DialogFragment()

            inputDialogFragment.show(childFragmentManager, inputDialogFragment::class.java.simpleName)
        }
    }
}
