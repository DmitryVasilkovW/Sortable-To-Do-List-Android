package com.sortabletodolist.sortabletodolistforandroid.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.presentation.scenarios.TaskScenario
import com.sortabletodolist.sortabletodolistforandroid.R
import com.sortabletodolist.sortabletodolistforandroid.ui.models.SharedViewModel
import kotlinx.coroutines.*

class EditTaskDialogFragment : BottomSheetDialogFragment()
{
    private lateinit var task: Task
    private lateinit var sharedViewModel: SharedViewModel

    companion object
    {
        fun newInstance(task: Task): EditTaskDialogFragment
        {
            val fragment = EditTaskDialogFragment()
            val args = Bundle()

            args.putSerializable("task", task)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        task = arguments?.getSerializable("task") as Task
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.dialog_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val taskNameEditText = view.findViewById<EditText>(R.id.taskNameEditText)
        val taskTextEditText = view.findViewById<EditText>(R.id.taskTextEditText)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)

        taskNameEditText.setText(task.name)
        taskTextEditText.setText(task.text)

        saveButton.setOnClickListener {
            val updatedTask = Task(
                id = task.id,
                name = taskNameEditText.text.toString(),
                text = taskTextEditText.text.toString(),
                isCompleted = task.isCompleted
            )

            lifecycleScope.launch(Dispatchers.IO)
            {
                sharedViewModel.updateTask(updatedTask, TaskScenario(requireContext()))
            }

            dismiss()
        }

        deleteButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO)
            {
                sharedViewModel.deleteTask(task, TaskScenario(requireContext()))
            }

            dismiss()
        }
    }
}