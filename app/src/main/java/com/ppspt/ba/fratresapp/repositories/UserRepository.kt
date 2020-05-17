package com.ppspt.ba.fratresapp.repositories

import androidx.lifecycle.LiveData
import com.ppspt.ba.fratresapp.dao.UserDao
import com.ppspt.ba.fratresapp.model.User

class UserRepository(private val userDao: UserDao) {

    fun getUser(name: String, surname: String, dateOfBirth: Long): LiveData<User> {
        return userDao.findUser(name, surname, dateOfBirth)
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) = instance ?: synchronized(this) {
            instance ?: UserRepository(userDao).also { instance = it }
        }
    }
}