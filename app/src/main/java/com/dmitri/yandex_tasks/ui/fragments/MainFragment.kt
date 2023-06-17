package com.dmitri.yandex_tasks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.adapter.TodoItemsAdapter
import com.dmitri.yandex_tasks.util.viewmodel.TodoItemsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    lateinit var todoItemsRecyclerView: RecyclerView
    private val todoItemsViewModel: TodoItemsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoItemsRecyclerView = view.findViewById(R.id.todoItemsList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = TodoItemsAdapter()
        todoItemsRecyclerView.adapter = adapter
        todoItemsRecyclerView.layoutManager = layoutManager
        adapter.todoItemsList = todoItemsViewModel.repository.todoList.value!!

        var addButton = requireActivity().findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            it.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToAddFragment(null))
        }
    }
}