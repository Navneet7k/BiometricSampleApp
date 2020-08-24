package com.example.touchnetbiometricsample.di.component

import android.app.Application
import com.example.touchnetbiometricsample.MvvmApp
import com.example.touchnetbiometricsample.di.modules.ActivityModule
import com.example.touchnetbiometricsample.di.modules.AppModule
import com.example.touchnetbiometricsample.di.modules.TouchnetDBModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        TouchnetDBModule::class]
)
interface   AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApplication: MvvmApp)

}
