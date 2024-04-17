package com.sortabletodolist.sortabletodolistforandroid.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sortabletodolist.presentation.scenarios.TaskScenario
import com.sortabletodolist.sortabletodolistforandroid.R
import com.sortabletodolist.sortabletodolistforandroid.ui.adapters.TaskAdapter
import com.sortabletodolist.sortabletodolistforandroid.ui.dialog.DialogFragment
import com.sortabletodolist.sortabletodolistforandroid.ui.models.SharedViewModel
import kotlinx.coroutines.launch

class TaskFragment : Fragment()
{
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fabAddTask: FloatingActionButton
    private val taskScenario: TaskScenario = TaskScenario()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        fabAddTask = view.findViewById(R.id.fab_add_task)

        fabAddTask.setOnClickListener {
            val dialogFragment = DialogFragment()
            dialogFragment.show(parentFragmentManager, "DialogFragment")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val tasks = taskScenario.getAllTasks(requireContext())

            sharedViewModel.updateTasks(tasks)
        }


        val taskListView = view.findViewById<ListView>(R.id.taskListView)

        sharedViewModel.tasks.observe(viewLifecycleOwner, { tasks ->
            val taskAdapter = TaskAdapter(tasks)

            taskListView.adapter = taskAdapter
        })
    }

}
