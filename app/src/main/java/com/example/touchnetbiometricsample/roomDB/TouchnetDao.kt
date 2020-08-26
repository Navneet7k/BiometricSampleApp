package com.example.touchnetbiometricsample.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.touchnetbiometricsample.model.SchoolsDM

@Dao
interface TouchnetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchoolData(data: List<SchoolsDM>)

    @Query("SELECT * from schools")
    fun getAllSchools() : LiveData<List<SchoolsDM>>
}