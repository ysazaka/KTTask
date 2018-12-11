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

import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.adapter.TaskListAdapter
import com.yakuzasqn.kttask.view.activity.TaskFormActivity
import kotlinx.android.synthetic.main.fragment_task_list.view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TaskListFragment : Fragment(), View.OnClickListener {

    private lateinit var mContext: Context
    private lateinit var mRecyclerView: RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(): TaskListFragment {
            return TaskListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        rootView.fab_add_task.setOnClickListener(this)
        mContext = rootView.context

        mRecyclerView = rootView.findViewById(R.id.rv_task_list)
        mRecyclerView.adapter = TaskListAdapter()
        mRecyclerView.layoutManager = LinearLayoutManager(mContext)

        return rootView
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab_add_task -> {
                startActivity(Intent(mContext, TaskFormActivity::class.java))
            }
        }
    }
}
