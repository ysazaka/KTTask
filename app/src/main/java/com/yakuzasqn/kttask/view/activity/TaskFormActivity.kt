package com.yakuzasqn.kttask.view.activity

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.business.PriorityBusiness
import com.yakuzasqn.kttask.business.TaskBusiness
import com.yakuzasqn.kttask.entity.PriorityEntity
import com.yakuzasqn.kttask.entity.TaskEntity
import com.yakuzasqn.kttask.util.SecurityPreferences
import com.yakuzasqn.kttask.util.TaskConstants
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences

    private val mSimpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    private var mPriorityListEntity: MutableList<PriorityEntity> = mutableListOf()
    private var mPriorityListId: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mPriorityBusiness = PriorityBusiness(this)
        mTaskBusiness = TaskBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)

        setListeners()
        loadPriorities()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_date -> {
                openDatePickerDialog()
            }

            R.id.btn_save -> {
                handleSave()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        btn_date.text = mSimpleDateFormat.format(calendar)
    }

    private fun setListeners() {
        btn_date.setOnClickListener(this)
        btn_save.setOnClickListener(this)
    }

    private fun handleSave(){

        try {

            val userId = mSecurityPreferences.getStorageString(TaskConstants.KEY.USER_ID).toInt()
            val priorityId = mPriorityListId[spin_priority.selectedItemPosition]
            val description = et_description.text.toString()
            val dueDate = btn_date.text.toString()
            val complete = check_complete.isChecked

            val taskEntity = TaskEntity(0, userId, priorityId, description, dueDate, complete)
            mTaskBusiness.insert(taskEntity)

            finish()

        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.unexpected_error), Toast.LENGTH_LONG).show()
        }

    }

    private fun openDatePickerDialog() {

        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, dayOfMonth).show()
    }

    private fun loadPriorities() {
        mPriorityListEntity = mPriorityBusiness.getList()
        val priorityList = mPriorityListEntity.map { it.description }
        mPriorityListId = mPriorityListEntity.map { it.id }.toMutableList()

        val adapter = ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, priorityList)
        spin_priority.adapter = adapter

    }
}
