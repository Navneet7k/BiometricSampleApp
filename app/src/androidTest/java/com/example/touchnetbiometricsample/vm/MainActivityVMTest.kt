package com.example.touchnetbiometricsample.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.touchnetbiometricsample.MainRepository
import com.example.touchnetbiometricsample.model.SchoolsDM
import com.example.touchnetbiometricsample.roomDB.TouchnetDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class MainActivityVMTest {

    private var touchnetDao: TouchnetDao? = null

    @Mock
    private val myRepository: MainRepository? = null


    @Before
    fun setUp() {
        touchnetDao = myRepository?.getTouchnetDao()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_School() {
        val school = SchoolsDM(1, "School image", "Sample School","School type","admin")
        touchnetDao?.insertSchool(school)
        val schoolTest = getValue(touchnetDao?.getSchool(school.id)!!)
        Assert.assertEquals(school.username, schoolTest.username)
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }
}