package com.sortabletodolist.sortabletodolistforandroid.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sortabletodolist.presentation.scenarios.TaskScenario
import com.sortabletodolist.sortabletodolistforandroid.R
import com.sortabletodolist.sortabletodolistforandroid.ui.models.SharedViewModel
import kotlinx.coroutines.launch

class DialogFragment : BottomSheetDialogFragment()
{
    private lateinit var taskNameEditText: EditText
    private lateinit var taskTextEditText: EditText
    private lateinit var isCompletedCheckBox: CheckBox
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button
    private lateinit var sharedViewModel: SharedViewModel
    private val taskScenario: TaskScenario = TaskScenario()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val view = inflater.inflate(R.layout.fragment_input_dialog, container, false)

        taskNameEditText = view.findViewById(R.id.taskNameEditText)
        taskTextEditText = view.findViewById(R.id.taskTextEditText)
        isCompletedCheckBox = view.findViewById(R.id.isCompletedCheckBox)
        addButton = view.findViewById(R.id.addButton)
        cancelButton = view.findViewById(R.id.cancel_button)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        addButton.setOnClickListener {
            val taskName = taskNameEditText.text.toString()
            val taskText = taskTextEditText.text.toString()
            val isCompleted = isCompletedCheckBox.isChecked

            lifecycleScope.launch {
                taskScenario.addTask(null, taskName, taskText, isCompleted, requireContext())
                updateTaskList()
            }

            Toast.makeText(context, "Задача добавлена", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    private suspend fun updateTaskList()
    {
        val tasks = taskScenario.getAllTasks(requireContext())

        sharedViewModel.updateTasks(tasks)
    }
}
