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
import com.sortabletodolist.data.repository.TaskRepository
import com.sortabletodolist.domain.models.Task
import com.sortabletodolist.interactors.TaskInteractor
import com.sortabletodolist.sortabletodolistforandroid.R
import com.sortabletodolist.sortabletodolistforandroid.ui.adapters.TaskAdapter
import com.sortabletodolist.sortabletodolistforandroid.ui.dialog.DialogFragment
import com.sortabletodolist.sortabletodolistforandroid.ui.models.SharedViewModel
import kotlinx.coroutines.launch

class TaskFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        // Получите ссылку на общую ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        fabAddTask = view.findViewById(R.id.fab_add_task)

        fabAddTask.setOnClickListener {
            val dialogFragment = DialogFragment()
            dialogFragment.show(parentFragmentManager, "DialogFragment")
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Предположим, что у вас есть ListView или RecyclerView для отображения задач
        val taskListView = view.findViewById<ListView>(R.id.taskListView)

        // Наблюдайте за LiveData в SharedViewModel
        sharedViewModel.tasks.observe(viewLifecycleOwner, { tasks ->
            // Создайте новый экземпляр вашего адаптера с новыми задачами
            val taskAdapter = TaskAdapter(tasks)

            // Установите новый адаптер для вашего ListView
            taskListView.adapter = taskAdapter
        })
    }

}
