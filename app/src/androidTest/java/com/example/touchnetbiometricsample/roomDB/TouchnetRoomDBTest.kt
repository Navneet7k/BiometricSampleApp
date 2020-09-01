package com.example.touchnetbiometricsample.roomDB

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.touchnetbiometricsample.model.SchoolsDM
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class TouchnetRoomDBTest {

    private lateinit var touchnetDao: TouchnetDao
    private lateinit var db: TouchnetRoomDB

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,TouchnetRoomDB::class.java).build()
        touchnetDao = db.touchnetDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun writeUserAndReadInList() {
        val school = SchoolsDM(1, "School image", "Sample School","School type","admin")
        touchnetDao.insertSchool(school)
        val byID =  touchnetDao.getSchool(school.id)!!

        assertThat(byID, equalTo(school))
    }
}