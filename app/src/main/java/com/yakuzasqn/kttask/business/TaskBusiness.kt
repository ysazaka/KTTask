package com.yakuzasqn.kttask.business

import android.content.Context
import com.yakuzasqn.kttask.entity.TaskEntity
import com.yakuzasqn.kttask.repository.TaskRepository

class TaskBusiness(val context: Context) {

    private val mTaskRepository: TaskRepository = TaskRepository.getInstance(context)

    fun getList(userId: Int): MutableList<TaskEntity> = mTaskRepository.getList(userId)

}