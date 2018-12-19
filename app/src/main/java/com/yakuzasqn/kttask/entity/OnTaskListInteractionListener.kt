package com.yakuzasqn.kttask.entity

interface OnTaskListInteractionListener {

    fun onListClick(taskId: Int)
    fun onDeleteClick(taskId: Int)

}