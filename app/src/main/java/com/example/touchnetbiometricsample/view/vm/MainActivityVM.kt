package com.example.touchnetbiometricsample.view.vm

import androidx.lifecycle.MutableLiveData
import com.example.touchnetbiometricsample.MainRepository
import com.example.touchnetbiometricsample.view.base.BaseViewModel
import javax.inject.Inject

class MainActivityVM @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {
    var errorToast= MutableLiveData<String>()
    var authStatus= MutableLiveData<Boolean>()
    var loginClick= MutableLiveData<Boolean>()

    fun onLogin() {
        loginClick.value = true
    }
}