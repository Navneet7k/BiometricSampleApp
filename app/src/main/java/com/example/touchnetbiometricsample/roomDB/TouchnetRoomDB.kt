package com.example.touchnetbiometricsample.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.touchnetbiometricsample.model.SchoolsDM
import java.util.concurrent.Executors

@Database(entities = [SchoolsDM::class], version = 1,exportSchema = false)
abstract class TouchnetRoomDB : RoomDatabase() {
    private val NUMBER_OF_THREADS = 4
    private val databaseWriteExcecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
    abstract fun touchnetDao(): TouchnetDao
}