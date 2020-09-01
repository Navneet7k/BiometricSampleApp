package com.example.touchnetbiometricsample.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.touchnetbiometricsample.model.SchoolsDM
import java.util.concurrent.Executors

@Database(entities = [SchoolsDM::class], version = 1,exportSchema = false)
abstract class TouchnetRoomDB : RoomDatabase() {
    abstract fun touchnetDao(): TouchnetDao
}