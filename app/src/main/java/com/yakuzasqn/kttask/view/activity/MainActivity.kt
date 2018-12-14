package com.yakuzasqn.kttask.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.business.PriorityBusiness
import com.yakuzasqn.kttask.repository.PriorityCacheConstants
import com.yakuzasqn.kttask.util.SecurityPreferences
import com.yakuzasqn.kttask.util.TaskConstants
import com.yakuzasqn.kttask.view.fragment.TaskListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var mPriorityBusiness: PriorityBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        mSecurityPreferences = SecurityPreferences(this)
        mPriorityBusiness = PriorityBusiness(this)

        loadPriorityCache()
        startDefaultFragment()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_done -> {
                fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.COMPLETE)
            }
            R.id.nav_todo -> {
                fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.TODO)
            }
            R.id.nav_logout -> {
                handleLogout()
            }
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fl_content, fragment!!).commit()

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startDefaultFragment(){
        val fragment: Fragment = TaskListFragment.newInstance(TaskConstants.TASKFILTER.COMPLETE)
        supportFragmentManager.beginTransaction().replace(R.id.fl_content, fragment).commit()
    }

    private fun loadPriorityCache(){
        PriorityCacheConstants.setCache(mPriorityBusiness.getList())
    }

    private fun handleLogout() {
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_ID)
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_NAME)
        mSecurityPreferences.removeStoredString(TaskConstants.KEY.USER_EMAIL)

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
