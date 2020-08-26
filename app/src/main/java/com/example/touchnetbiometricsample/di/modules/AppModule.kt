package com.example.touchnetbiometricsample.di.modules

import android.app.Application
import android.content.Context
import com.example.touchnetbiometricsample.MainRepository
import com.example.touchnetbiometricsample.R
import com.example.touchnetbiometricsample.roomDB.TouchnetDao
import dagger.Module
import dagger.Provides
import io.github.inflationx.calligraphy3.CalligraphyConfig
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule{

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideMainRepository(touchnetDao: TouchnetDao) : MainRepository {
        return MainRepository(touchnetDao)
    }
}

