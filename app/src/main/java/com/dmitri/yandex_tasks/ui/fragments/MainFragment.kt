package com.dmitri.yandex_tasks.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.TaskApplication
import com.dmitri.yandex_tasks.adapter.TodoItemsAdapter
import com.dmitri.yandex_tasks.util.repository.TodoItemsRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    lateinit var todoItemsRecyclerView: RecyclerView
    lateinit var todoItemsRepository: TodoItemsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoItemsRepository = (requireActivity().application as TaskApplication).todoItemsRepository
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
        adapter.todoItemsList = todoItemsRepository.getItems(requireContext())
        Log.i("INFO", "onViewCreated visit")

        var addButton = requireActivity().findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }
}