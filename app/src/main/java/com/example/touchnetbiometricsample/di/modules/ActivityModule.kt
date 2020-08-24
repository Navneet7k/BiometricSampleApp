package com.example.touchnetbiometricsample.di.modules


import com.example.touchnetbiometricsample.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All Activity must be specified here
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

}

