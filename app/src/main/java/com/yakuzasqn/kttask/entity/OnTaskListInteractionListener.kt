package com.yakuzasqn.kttask.entity

interface OnTaskListInteractionListener {

    fun onListClick(taskId: Int)
    fun onDeleteClick(taskId: Int)
    fun onNotCompleteClick(taskId: Int)
    fun onCompleteClick(taskId: Int)

}