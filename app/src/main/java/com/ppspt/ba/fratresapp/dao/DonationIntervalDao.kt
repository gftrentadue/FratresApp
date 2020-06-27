package com.ppspt.ba.fratresapp.dao

import androidx.room.*
import com.ppspt.ba.fratresapp.model.DonationInterval
import com.ppspt.ba.fratresapp.utility.UserConverters

@Dao
interface DonationIntervalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(intervals: List<DonationInterval>)

    @Delete
    fun delete(interval: DonationInterval)

    @Query("DELETE FROM intervals_table")
    fun deleteAll()
}