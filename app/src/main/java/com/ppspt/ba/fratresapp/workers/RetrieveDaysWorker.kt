package com.ppspt.ba.fratresapp.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ppspt.ba.fratresapp.dao.DonationDayDao
import com.ppspt.ba.fratresapp.db.AppDatabase
import com.ppspt.ba.fratresapp.model.DonationDay
import kotlinx.coroutines.coroutineScope

class RetrieveDaysWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        val database = AppDatabase.getInstance(applicationContext)

        val dao = database.donationDayDao()
        dao.deleteAll()

        fillDatabase(dao)

        Result.success()
    }

    private suspend fun fillDatabase(dao: DonationDayDao) {
        val dayList = arrayListOf<DonationDay>()
        var id = 0
        // 05/01/2020 PARROCCHIA SANT'AGOSTINO
        dayList.add(
            DonationDay(
                id,
                5,
                1,
                2020,
                "Parrocchia Sant'Agostino, Via Monte Pertica, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++

        // 18/01/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                18,
                1,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++

        // 21/02/2020 ISTITUTO TOMMASO FIORE
        dayList.add(
            DonationDay(
                id,
                21,
                2,
                2020,
                "IISS Tommaso Fiore, Via Padre Annibale Maria di Francia, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 15/03/2020 PARROCCHIA IMMACOLATA
        dayList.add(
            DonationDay(
                id,
                15,
                3,
                2020,
                "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 04/04/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                4,
                4,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 17/05/2020 FRETRES
        dayList.add(
            DonationDay(
                id,
                17,
                5,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 30/05/2020 PARROCCHIA SANT'OTTAVIO
        dayList.add(
            DonationDay(
                id,
                30,
                5,
                2020,
                "Via Magna Grecia, 19, 70026 Modugno BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 13/06/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                13,
                6,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 04/09/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                4,
                9,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 26/09/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                26,
                9,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 16/10/2020 ISTITUTO TOMMASO FIORE
        dayList.add(
            DonationDay(
                id,
                16,
                10,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 07/11/2020 PARROCCHIA SANT'OTTAVIO
        dayList.add(
            DonationDay(
                id,
                7,
                11,
                2020,
                "Via Magna Grecia, 19, 70026 Modugno BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 29/11/2020 FRATRES
        dayList.add(
            DonationDay(
                id,
                29,
                11,
                2020,
                "Via X Marzo, 92, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        id++
        // 12/12/2020 PARROCCHIA IMMACOLATA
        dayList.add(
            DonationDay(
                id,
                12,
                12,
                2020,
                "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA",
                8,
                0,
                12,
                0,
                20,
                arrayListOf()
            )
        )

        dao.insertAll(dayList)
    }

}