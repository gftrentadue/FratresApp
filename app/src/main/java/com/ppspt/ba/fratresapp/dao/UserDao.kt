package com.ppspt.ba.fratresapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.ppspt.ba.fratresapp.model.User
import com.ppspt.ba.fratresapp.utility.UserConverters

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE name LIKE :name AND surname LIKE :surname AND dateOfBirth == :dateOfBirth")
    fun findUser(name: String, surname: String, dateOfBirth: Long): LiveData<User>

    @Insert
    fun insertUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}