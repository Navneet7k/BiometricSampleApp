package com.example.touchnetbiometricsample.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.touchnetbiometricsample.view.model.SchoolsDM

@Dao
interface TouchnetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchoolData(data: List<SchoolsDM>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchool(data: SchoolsDM)

    @Query("SELECT * from schools")
    fun getAllSchools() : LiveData<List<SchoolsDM>>

    @Query("SELECT * FROM schools WHERE id=:id")
    fun getSchool(id: Int) : LiveData<SchoolsDM>
}