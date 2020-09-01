package com.example.touchnetbiometricsample

import androidx.lifecycle.LiveData
import com.example.touchnetbiometricsample.roomDB.TouchnetDao
import com.example.touchnetbiometricsample.model.SchoolsDM
import com.example.touchnetbiometricsample.roomDB.TouchnetRoomDB
import java.util.concurrent.Executors
import javax.inject.Inject

class MainRepository @Inject constructor( private val touchnetDao: TouchnetDao): BaseRepository() {

    val databaseWriteExcecutor = Executors.newFixedThreadPool(4)

    fun getTouchnetDao() : TouchnetDao = touchnetDao

    fun getAllSchools() : LiveData<List<SchoolsDM>> {
        return touchnetDao.getAllSchools()
    }

    fun insertSchool(schoolsDM: SchoolsDM)  {
        databaseWriteExcecutor.execute {
            touchnetDao.insertSchool(schoolsDM)
        }
    }
}