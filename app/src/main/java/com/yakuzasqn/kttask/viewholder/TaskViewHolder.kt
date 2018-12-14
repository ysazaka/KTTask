package com.yakuzasqn.kttask.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.entity.TaskEntity
import com.yakuzasqn.kttask.repository.PriorityCacheConstants

class TaskViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    private val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
    private val tvPriority: TextView = itemView.findViewById(R.id.tv_priority)
    private val tvDueDate: TextView = itemView.findViewById(R.id.tv_duedate)
    private val ivTask: ImageView = itemView.findViewById(R.id.iv_task)

    fun bindData(task: TaskEntity) {
        tvDescription.text = task.description
        tvPriority.text = PriorityCacheConstants.getPriorityDescription(task.priorityId)
        tvDueDate.text = task.dueDate

        if (task.complete) {
            ivTask.setImageResource(R.drawable.ic_done)
        }
    }

}