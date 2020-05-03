package com.ppspt.ba.fratresapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBLoader(ctx: Context, databaseName: String, version: Int) {
    private var dbVersion = version
    private var dbHelper: DBHelper

    init {
        dbHelper = DBHelper(ctx, databaseName, null, dbVersion)
    }

    class DBHelper(
        context: Context,
        databaseName: String,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
    ) : SQLiteOpenHelper(context, databaseName, factory, version) {
        /*private val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DonationDayTable.DAY_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.MONTH_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.YEAR_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.ADDRESS_COLUMN_NAME} TEXT," +
                    "${DonationDayTable.STHOUR_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.STMINUTE_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.FTHOUR_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.FTMINUTE_COLUMN_NAME} INTEGER," +
                    "${DonationDayTable.INTERVAL_COLUMN_NAME} INTEGER)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DonationDayTable.TABLE_NAME}"*/

        override fun onCreate(db: SQLiteDatabase?) {
            //db?.execSQL(SQL_CREATE_ENTRIES)
            //fillDatabase()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            //db?.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }

        private fun fillDatabase() {
            val database = this.writableDatabase

            /*// 05/01/2020 PARROCCHIA SANT'AGOSTINO
            var column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 5)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 0)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "Parrocchia Sant'Agostino, Via Monte Pertica, Modugno, BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 18/01/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 18)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 0)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 21/02/2020 ISTITUTO TOMMASO FIORE
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 21)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 1)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "IISS Tommaso Fiore, Via Padre Annibale Maria di Francia, Modugno, BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 15/03/2020 PARROCCHIA IMMACOLATA
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 15)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 2)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 04/04/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 4)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 3)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 17/05/2020 FRETRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 17)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 4)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 30/05/2020 PARROCCHIA SANT'OTTAVIO
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 30)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 4)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "Via Magna Grecia, 19, 70026 Modugno BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 13/06/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 13)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 5)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 04/09/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 4)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 8)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 26/09/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 26)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 8)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 16/10/2020 ISTITUTO TOMMASO FIORE
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 16)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 9)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "IISS Tommaso Fiore, Via Padre Annibale Maria di Francia, Modugno, BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 07/11/2020 PARROCCHIA SANT'OTTAVIO
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 7)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 10)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "Via Magna Grecia, 19, 70026 Modugno BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 29/11/2020 FRATRES
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 29)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 10)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(DonationDayTable.ADDRESS_COLUMN_NAME, "Via X Marzo, 92, Modugno, BA")
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)

            // 12/12/2020 PARROCCHIA IMMACOLATA
            column = ContentValues()
            column.put(DonationDayTable.DAY_COLUMN_NAME, 12)
            column.put(DonationDayTable.MONTH_COLUMN_NAME, 11)
            column.put(DonationDayTable.YEAR_COLUMN_NAME, 2020)
            column.put(
                DonationDayTable.ADDRESS_COLUMN_NAME,
                "Parrocchia Immacolata Modugno, Viale della Repubblica, Modugno, BA"
            )
            column.put(DonationDayTable.STHOUR_COLUMN_NAME, 8)
            column.put(DonationDayTable.STMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.FTHOUR_COLUMN_NAME, 12)
            column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, 0)
            column.put(DonationDayTable.INTERVAL_COLUMN_NAME, 20)
            database.insert(DonationDayTable.TABLE_NAME, null, column)*/

            database.close()
        }

        fun upgradeDatabase(oldVersion: Int, newVersion: Int) {
            onUpgrade(this.writableDatabase, oldVersion, newVersion)
        }

        /*fun addColumn(contentValues: ContentValues) {
            val db = this.writableDatabase
            db.insert(DonationDayTable.TABLE_NAME, null, contentValues)
            db.close()
        }*/
    }

    fun upgradeDatabase(newVersion: Int) {
        if (newVersion > dbVersion)
            dbHelper.upgradeDatabase(dbVersion, newVersion)
        else {
            Log.e("DB_UPGRADE_ERROR", "!Old version is grater than current version!")
        }
    }

    /*fun addColumn(data: DonationDayEntry) {
        val column = ContentValues()
        column.put(DonationDayTable.DAY_COLUMN_NAME, data.day)
        column.put(DonationDayTable.MONTH_COLUMN_NAME, data.month)
        column.put(DonationDayTable.YEAR_COLUMN_NAME, data.year)
        column.put(DonationDayTable.ADDRESS_COLUMN_NAME, data.address)
        column.put(DonationDayTable.STHOUR_COLUMN_NAME, data.startTimeHour)
        column.put(DonationDayTable.STMINUTE_COLUMN_NAME, data.startTimeMinute)
        column.put(DonationDayTable.FTHOUR_COLUMN_NAME, data.finishTimeHour)
        column.put(DonationDayTable.FTMINUTE_COLUMN_NAME, data.finishTimeMinute)
        column.put(DonationDayTable.INTERVAL_COLUMN_NAME, data.interval)
        dbHelper.addColumn(column)
    }*/
}