package com.dmitri.yandex_tasks.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dmitri.yandex_tasks.R
import com.dmitri.yandex_tasks.databinding.FragmentAddBinding
import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.dmitri.yandex_tasks.util.viewmodel.TodoItemsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.UUID

class AddFragment : Fragment() {

    private val DATE_PATTERN = "d MMMM uuuu"

    private val todoItemsViewModel: TodoItemsViewModel by viewModels()

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val args: AddFragmentArgs by navArgs()
    private val todoItem by lazy { args.todoItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton = binding.saveButton
        val checkbox = binding.dateSwitch
        val hiden = binding.hiddenDatePicker
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                hiden.visibility = View.VISIBLE
            } else {
                hiden.visibility = View.INVISIBLE
            }
        }
        hiden.setOnClickListener { view ->
            openDialog(view)
        }
        binding.chooseImportance.setOnClickListener {
            showPopup(it)
        }

        if (todoItem == null) {
            binding.deleteButton.isEnabled = false
        } else {
            Log.i("i", todoItem.toString())
            binding.inputDescription.setText(todoItem!!.description)
            Log.i("i", binding.inputDescription.text.toString())
            binding.chosenImportance.text = todoItem!!.priority.text
            if (todoItem!!.deadline != null) {
                binding.dateSwitch.isChecked = true
                binding.hiddenDatePicker.text =
                    todoItem!!.deadline!!.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
            }

            setDeleteButtonListener(todoItem!!)
        }

        binding.closeButton.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
        }

        binding.apply {
            saveButton.setOnClickListener {
                if (inputDescription.text.toString().isEmpty()) {
                    Toast.makeText(context, "it's empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val description = inputDescription.text.toString()
                val importance = when (chosenImportance.text) {
                    "Нет" -> Priority.LOW
                    "Средняя" -> Priority.MEDIUM
                    "Высокая" -> Priority.HIGH
                    else -> Priority.LOW
                }

                var deadline: LocalDate? = null

                try {
                    deadline = LocalDate.parse(
                        hiddenDatePicker.text,
                        DateTimeFormatter.ofPattern(DATE_PATTERN)
                    )
                    if (!dateSwitch.isChecked) {
                        deadline = null
                        hiddenDatePicker.setText(R.string.input_date)
                    }
                } catch (_: DateTimeParseException) {
                }

                if (todoItem != null) {
                    todoItem!!.description = description
                    todoItem!!.priority = importance
                    todoItem!!.deadline = deadline
                    todoItem!!.modificationDate = LocalDate.now()
                    todoItemsViewModel.update(todoItem!!)
                } else {
                    todoItemsViewModel.insert(
                        TodoItem(
                            UUID.randomUUID(),
                            description,
                            importance,
                            deadline,
                            false,
                            LocalDate.now(),
                            LocalDate.now()
                        )
                    )
                }



                findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
            }
        }
    }

    private fun openDialog(view: View) {
        var now = LocalDate.now()
        val datePicker = DatePickerDialog(
            requireContext(),
            { datePicker, year, month, day ->
                var txt = view.findViewById<TextView>(R.id.hiddenDatePicker)
                val date = LocalDate.of(year, month, day)
                txt.text = date.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
            },
            now.year,
            now.monthValue,
            now.dayOfMonth
        )
        datePicker.show()
    }

    private fun showPopup(view: View) {
        var popupMenu = PopupMenu(requireContext(), view)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.low -> binding.chosenImportance.text = menuItem.title
                R.id.medium -> binding.chosenImportance.text = menuItem.title
                R.id.high -> binding.chosenImportance.text = menuItem.title
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.inflate(R.menu.importance_popup_menu)
        popupMenu.show()
    }

    private fun setDeleteButtonListener(todoItem: TodoItem) {
        binding.deleteButton.setOnClickListener {
            todoItemsViewModel.delete(todoItem)
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
        }
    }
}