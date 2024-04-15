package com.sortabletodolist.sortabletodolistforandroid.ui.tasks

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sortabletodolist.sortabletodolistforandroid.databinding.FragmentTasksBinding

class TasksFragment : Fragment()
{
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private var savedText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTasks
        tasksViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val fab: FloatingActionButton = binding.fabAddTask
        fab.setOnClickListener {
            showInputDialog()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Введите текст")

        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            savedText = input.text.toString()
            Toast.makeText(context, "Текст сохранен", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}
