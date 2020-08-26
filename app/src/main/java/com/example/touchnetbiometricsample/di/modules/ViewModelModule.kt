package com.example.touchnetbiometricsample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.touchnetbiometricsample.di.ViewModelFactory
import com.example.touchnetbiometricsample.di.ViewModelKey
import com.example.touchnetbiometricsample.vm.MainActivityVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * All viewmodel class must be specified here
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    internal abstract fun bindMainActivityVM(mainActivityVM: MainActivityVM): ViewModel

    @Binds
    internal abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
