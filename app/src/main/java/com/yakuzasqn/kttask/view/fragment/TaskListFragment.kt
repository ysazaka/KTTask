package com.yakuzasqn.kttask.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.adapter.TaskListAdapter
import com.yakuzasqn.kttask.business.TaskBusiness
import com.yakuzasqn.kttask.entity.OnTaskListInteractionListener
import com.yakuzasqn.kttask.util.SecurityPreferences
import com.yakuzasqn.kttask.util.TaskConstants
import com.yakuzasqn.kttask.view.activity.TaskFormActivity
import kotlinx.android.synthetic.main.fragment_task_list.view.*

class TaskListFragment : Fragment(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var mListener: OnTaskListInteractionListener
    private var mTaskFilter: Int = 0

    companion object {

        fun newInstance(taskFilter: Int): TaskListFragment {
            val args: Bundle = Bundle()
            args.putInt(TaskConstants.TASKFILTER.KEY, taskFilter)

            val fragment = TaskListFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            mTaskFilter = arguments!!.getInt(TaskConstants.TASKFILTER.KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        rootView.fab_add_task.setOnClickListener(this)
        mContext = rootView.context

        mTaskBusiness = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)
        mListener = object: OnTaskListInteractionListener {

            override fun onListClick(taskId: Int) {
                val bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID, taskId)

                val intent = Intent(mContext, TaskFormActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDeleteClick(taskId: Int) {
                mTaskBusiness.delete(taskId)
                loadTasks()
                Toast.makeText(mContext, getString(R.string.task_delete_success), Toast.LENGTH_SHORT).show()
            }

        }

        mRecyclerView = rootView.findViewById(R.id.rv_task_list)
        mRecyclerView.adapter = TaskListAdapter(mutableListOf(), mListener)
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_task -> {
                startActivity(Intent(mContext, TaskFormActivity::class.java))
            }
        }
    }

    private fun loadTasks(){
        mRecyclerView.adapter = TaskListAdapter(mTaskBusiness.getList(mTaskFilter), mListener)
    }
}
