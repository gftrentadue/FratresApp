package com.ppspt.ba.fratresapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ppspt.ba.fratresapp.dao.DonationDayDao
import com.ppspt.ba.fratresapp.dao.DonationIntervalDao
import com.ppspt.ba.fratresapp.dao.UserDao
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.model.DonationInterval
import com.ppspt.ba.fratresapp.model.User
import com.ppspt.ba.fratresapp.utility.DonationConverters
import com.ppspt.ba.fratresapp.utility.UserConverters
import com.ppspt.ba.fratresapp.workers.RetrieveDaysWorker

const val DATABASE_NAME = "fratres_db"

@Database(
    entities = [DonationDay::class, User::class, DonationInterval::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserConverters::class, DonationConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun donationDayDao(): DonationDayDao
    abstract fun userDao(): UserDao
    abstract fun donationIntervalDao(): DonationIntervalDao

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