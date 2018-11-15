package com.yakuzasqn.kttask.view.activity

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.business.PriorityBusiness
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var mPriorityBusiness: PriorityBusiness
    private val mSimpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mPriorityBusiness = PriorityBusiness(this)

        setListeners()
        loadPriorities()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_date -> {
                openDatePickerDialog()
            }

            R.id.btn_save -> {

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

    private fun openDatePickerDialog() {

        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, this, year, month, dayOfMonth).show()
    }

    private fun loadPriorities() {
        val priorityListEntity = mPriorityBusiness.getList()
        val priorityList = priorityListEntity.map { it.description }

        val adapter = ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, priorityList)
        spin_priority.adapter = adapter

    }
}
