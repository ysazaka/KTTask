package com.yakuzasqn.kttask.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.viewholder.TaskViewHolder

class TaskListAdapter: RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_task_list, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(p0: TaskViewHolder, p1: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

}