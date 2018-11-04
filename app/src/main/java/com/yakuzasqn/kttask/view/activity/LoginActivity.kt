package com.yakuzasqn.kttask.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserBusiness: UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBusiness = UserBusiness(this)

        setListeners()
    }

    private fun setListeners() {
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login -> {
                handleLogin()
            }
        }
    }

    private fun handleLogin() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()

        if (mUserBusiness.login(email, password)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show()
        }
    }
}
