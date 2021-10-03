package com.example.kotlintasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: taskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskAdapter = TaskAdapter(mutableListOf())

        rvTaskItems.adapter = taskAdapter
        rvTaskItems.layoutManager = LinearLayoutManager(this)

        btnAddTask.setOnClickListener {
            val taskTitle = etTaskTitle.text.toString()
            if(taskTitle.isNotEmpty()) {
                val todo = Todo(taskTitle)
                todoAdapter.addTodo(todo)
                etTaskTitle.text.clear()
            }
        }
        btnDeleteTask.seOnClickListener {
            todoAdapter.deleteCompleteTasks()
        }
    }
}