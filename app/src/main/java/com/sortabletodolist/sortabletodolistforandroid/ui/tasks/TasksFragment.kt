package com.sortabletodolist.sortabletodolistforandroid.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sortabletodolist.sortabletodolistforandroid.databinding.FragmentTasksBinding
import com.sortabletodolist.sortabletodolistforandroid.ui.dialog.InputDialogFragment

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
        tasksViewModel.text.observe(viewLifecycleOwner, Observer
        {
            textView.text = it
        })

        val fab: FloatingActionButton = binding.fabAddTask
        fab.setOnClickListener {
            val dialog = InputDialogFragment()
            dialog.show(parentFragmentManager, "InputDialogFragment")
        }


        return root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}
