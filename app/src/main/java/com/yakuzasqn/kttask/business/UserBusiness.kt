package com.yakuzasqn.kttask.business

import android.content.Context
import com.yakuzasqn.kttask.R
import com.yakuzasqn.kttask.entity.UserEntity
import com.yakuzasqn.kttask.util.SecurityPreferences
import com.yakuzasqn.kttask.util.TaskConstants
import com.yakuzasqn.kttask.util.ValidationException
import repository.UserRepository

class UserBusiness(val context: Context) {

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun login(email: String, password: String): Boolean {

        val user: UserEntity? = mUserRepository.get(email, password)

        return if (user != null){
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, user.name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, user.email)

            true
        } else
            false
    }

    fun insert(name: String, email: String, password: String) {

        try {
            if (name == "" || email == "" || password == ""){
                throw ValidationException("Informe todos os campos")
            }

            if (mUserRepository.isEmailExistent(email)){
                throw ValidationException(context.getString(R.string.email_exists))
            }

            val userId = mUserRepository.insert(name, email, password)

            // Save the user data on shared preferences
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, userId.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, email)
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

}