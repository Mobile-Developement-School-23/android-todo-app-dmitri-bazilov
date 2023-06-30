package com.dmitri.yandex_tasks.main_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.TaskApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    lateinit var todoItemsRecyclerView: RecyclerView
    private val todoListViewModel: TodoListViewModel by viewModels()

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

        initRecyclerView(view)

        initToolbar(view)

        var visibilityButton: AppCompatImageButton = view.findViewById(R.id.visibilityButton)
        var visFlag = (requireActivity().application as TaskApplication).showChecked
        if (visFlag) {
            visibilityButton.setImageResource(R.drawable.visible_button)
        } else {
            visibilityButton.setImageResource(R.drawable.visibility_off_button)
        }
        setVisibilityButtonListener(visibilityButton)

        var addButton = requireActivity().findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            it.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToAddFragment(null))
        }

        todoListViewModel.updateTodoList()

    }

    private fun initRecyclerView(view: View) {
        todoItemsRecyclerView = view.findViewById(R.id.todoItemsList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = todoListViewModel.todoListAdapter
        todoItemsRecyclerView.adapter = adapter
        todoItemsRecyclerView.layoutManager = layoutManager


    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolBar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun setVisibilityButtonListener(visButton: AppCompatImageButton) {
        visButton.setOnClickListener { view ->
            (requireActivity().application as TaskApplication).showChecked =
                (requireActivity().application as TaskApplication).showChecked.not()
            val visFlag = (requireActivity().application as TaskApplication).showChecked
            if (visFlag) {
                visButton.setImageResource(R.drawable.visible_button)
            } else {
                visButton.setImageResource(R.drawable.visibility_off_button)
            }
        }
    }
}