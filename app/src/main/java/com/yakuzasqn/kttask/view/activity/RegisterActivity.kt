package com.yakuzasqn.kttask.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.business.UserBusiness
import com.yakuzasqn.kttask.util.ValidationException
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    // lateinit is used here because the UserBusiness class requires that we pass a context, so
    // the RegisterActivity needs to pass through onCreate first in order to become a real
    // Activity, and then, it's assigned a value to mUserBusiness

    private lateinit var mUserBusiness : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Events
        setListeners()

        // Instantiation of variables of the class
        mUserBusiness = UserBusiness(this)
    }

    private fun setListeners() {
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_register -> {
                handleRegister()
            }
        }
    }

    private fun handleRegister(){

        try {

            val name = et_name.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            // Create a new user
            mUserBusiness.insert(name, email, password)

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        } catch (e: ValidationException){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
            Toast.makeText(this, "Algo de errado aconteceu, tente novamente", Toast.LENGTH_SHORT).show()
        }

    }
}
