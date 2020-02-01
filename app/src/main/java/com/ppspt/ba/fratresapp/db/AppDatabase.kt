package com.ppspt.ba.fratresapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ppspt.ba.fratresapp.dao.DonationDayDao
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.model.TABLE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DonationDay::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun donationDayDao(): DonationDayDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    TABLE_NAME
                ).addCallback(DonationDayCallback(scope)).build()

                INSTANCE = instance

                instance
            }
        }
    }

    private class DonationDayCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { dao ->
                scope.launch(Dispatchers.IO) {
                    val donationDayDao = dao.donationDayDao()

                    donationDayDao.deleteAll()

                    fillDatabase(dao.donationDayDao())

                }
            }
        }

        suspend fun fillDatabase(dao: DonationDayDao) {
            val dayList = arrayListOf<DonationDay>()
            var id = 0
            // 05/01/2020 PARROCCHIA SANT'AGOSTINO
            dayList.add(
                DonationDay(
                    id,
                    5,
                    0,
                    2020,
                    "Parrocchia Sant'Agostino, Via Monte Pertica, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++

            // 18/01/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    18,
                    0,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++

            // 21/02/2020 ISTITUTO TOMMASO FIORE
            dayList.add(
                DonationDay(
                    id,
                    21,
                    1,
                    2020,
                    "IISS Tommaso Fiore, Via Padre Annibale Maria di Francia, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 15/03/2020 PARROCCHIA IMMACOLATA
            dayList.add(
                DonationDay(
                    id,
                    15,
                    2,
                    2020,
                    "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 04/04/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    4,
                    3,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 17/05/2020 FRETRES
            dayList.add(
                DonationDay(
                    id,
                    17,
                    4,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 30/05/2020 PARROCCHIA SANT'OTTAVIO
            dayList.add(
                DonationDay(
                    id,
                    30,
                    4,
                    2020,
                    "Via Magna Grecia, 19, 70026 Modugno BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 13/06/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    13,
                    5,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 04/09/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    4,
                    8,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 26/09/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    26,
                    8,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 16/10/2020 ISTITUTO TOMMASO FIORE
            dayList.add(
                DonationDay(
                    id,
                    16,
                    9,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 07/11/2020 PARROCCHIA SANT'OTTAVIO
            dayList.add(
                DonationDay(
                    id,
                    7,
                    10,
                    2020,
                    "Via Magna Grecia, 19, 70026 Modugno BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 29/11/2020 FRATRES
            dayList.add(
                DonationDay(
                    id,
                    29,
                    10,
                    2020,
                    "Via X Marzo, 92, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            id++
            // 12/12/2020 PARROCCHIA IMMACOLATA
            dayList.add(
                DonationDay(
                    id,
                    12,
                    11,
                    2020,
                    "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA",
                    8,
                    0,
                    12,
                    0,
                    20
                )
            )

            dao.insertAll(dayList)
        }
    }
}