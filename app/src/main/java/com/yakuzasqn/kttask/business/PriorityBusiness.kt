package com.yakuzasqn.kttask.business

import android.content.Context
import com.yakuzasqn.kttask.entity.PriorityEntity
import com.yakuzasqn.kttask.repository.PriorityRepository

class PriorityBusiness(val context: Context) {

    private val mPriorityRepository : PriorityRepository = PriorityRepository.getInstance(context)

    fun getList(): MutableList<PriorityEntity> = mPriorityRepository.getList()

}