package com.example.touchnetbiometricsample.di.modules

import android.app.Application
import androidx.room.Room
import com.example.touchnetbiometricsample.DATABASE_NAME
import com.example.touchnetbiometricsample.roomDB.TouchnetDao
import com.example.touchnetbiometricsample.roomDB.TouchnetRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TouchnetDBModule {

    @Provides
    @Singleton
    internal fun provideTouchnetRoomDB(application: Application) : TouchnetRoomDB {
        return Room.databaseBuilder(application,TouchnetRoomDB::class.java,
            DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    internal fun provideTouchnetDao(touchnetRoomDB: TouchnetRoomDB) : TouchnetDao {
        return touchnetRoomDB.touchnetDao()
    }

}