package com.ppspt.ba.fratresapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ppspt.ba.fratresapp.dao.DonationDayDao
import com.ppspt.ba.fratresapp.dao.UserDao
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.model.User
import com.ppspt.ba.fratresapp.workers.RetrieveDaysWorker

const val DATABASE_NAME = "fratres_db"

@Database(entities = [DonationDay::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun donationDayDao(): DonationDayDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(DonationDayCallback(context)).build()

                INSTANCE = instance

                instance
            }
        }
    }

    private class DonationDayCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            val request = OneTimeWorkRequestBuilder<RetrieveDaysWorker>().build()

            WorkManager.getInstance(context).enqueue(request)
        }

    }
}