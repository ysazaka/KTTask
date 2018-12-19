package com.yakuzasqn.kttask.business

import android.content.Context
import com.yakuzasqn.kttask.entity.TaskEntity
import com.yakuzasqn.kttask.repository.TaskRepository
import com.yakuzasqn.kttask.util.SecurityPreferences
import com.yakuzasqn.kttask.util.TaskConstants

class TaskBusiness(val context: Context) {

    private val mTaskRepository: TaskRepository = TaskRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun get(id: Int) = mTaskRepository.get(id)

    fun getList(mTaskFilter: Int): MutableList<TaskEntity> {
        val userId = mSecurityPreferences.getStorageString(TaskConstants.KEY.USER_ID).toInt()
        return mTaskRepository.getList(userId, mTaskFilter)
    }

    fun insert(taskEntity: TaskEntity) = mTaskRepository.insert(taskEntity)

    fun update(taskEntity: TaskEntity) = mTaskRepository.update(taskEntity)

    fun delete(taskId: Int) = mTaskRepository.delete(taskId)

    fun complete(taskId: Int, isComplete: Boolean) {
        val task = mTaskRepository.get(taskId)
        if (task != null){
            task.complete = isComplete
            mTaskRepository.update(task)
        }
    }

}