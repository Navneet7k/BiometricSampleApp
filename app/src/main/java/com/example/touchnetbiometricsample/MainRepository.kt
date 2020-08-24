package com.example.touchnetbiometricsample

import androidx.lifecycle.LiveData
import com.example.touchnetbiometricsample.roomDB.TouchnetDao
import com.example.touchnetbiometricsample.view.model.SchoolsDM
import javax.inject.Inject

class MainRepository @Inject constructor( private val touchnetDao: TouchnetDao): BaseRepository() {

    fun getAllSchools() : LiveData<List<SchoolsDM>> {
        return touchnetDao.getAllSchools()
    }
}