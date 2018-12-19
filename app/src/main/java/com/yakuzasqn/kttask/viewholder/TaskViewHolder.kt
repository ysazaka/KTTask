package com.yakuzasqn.kttask.viewholder

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.entity.OnTaskListInteractionListener
import com.yakuzasqn.kttask.entity.TaskEntity
import com.yakuzasqn.kttask.repository.PriorityCacheConstants

class TaskViewHolder (itemView: View, val context: Context, val listener: OnTaskListInteractionListener): RecyclerView.ViewHolder(itemView) {

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

        // Click listener for data update
        tvDescription.setOnClickListener {
            listener.onListClick(task.id)
        }

        tvDescription.setOnLongClickListener {
            showConfirmationDialog(task)
            true
        }

        ivTask.setOnClickListener {
            if (task.complete){
                listener.onNotCompleteClick(task.id)
            } else {
                listener.onCompleteClick(task.id)
            }
        }
    }

    private fun showConfirmationDialog(task: TaskEntity){
        listener.onDeleteClick(task.id)

        AlertDialog.Builder(context)
                .setTitle("Remoção de tarefa")
                .setMessage("Deseja remover ${task.description}?")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Sim") { dialog, which -> listener.onDeleteClick(task.id) }
                .setNegativeButton("Cancelar", null).show()
    }

}