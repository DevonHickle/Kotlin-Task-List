package com.example.kotlintasklist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (
    private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


   class TaskViewHolder(itemView: View, b: Boolean) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate (
            R.layout.item_task,
            parent,
            false
            )
        )
    }
    //Function to add tasks to list
    fun addTask(task: Task) {
        task.add(task)
        notifyItemInserted( task.size - 1)
    }
    //Function to delete tasks from list
    fun deleteCompleteTasks() {
        todo.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val curTask = todo[position]
            holder.itemView.apply {
                tvTaskTitle.text = curTask.title
                cbDone.isChecked = curTask.isChecked
                toggleStrikeThrough(tvTaskTitle, curTask.isChecked)
                cbDone.setonCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(tvTaskTitle, isChecked)
                    curTask.isChecked = !curTask.isChecked
                }
            }
    }

    //Function for strikethrough animation toggle on completed tasks
    private fun toggleStrikeThrough(tvTaskTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTaskTitle.paintFlags = tvTaskTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}