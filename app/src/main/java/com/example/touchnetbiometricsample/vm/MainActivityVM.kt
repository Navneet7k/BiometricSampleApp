package com.example.touchnetbiometricsample.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityVM : ViewModel() {
    public var errorToast= MutableLiveData<String>()
    public var authStatus= MutableLiveData<Boolean>()
    public var loginClick= MutableLiveData<Boolean>()

    public fun onLogin() {
        loginClick.value = true
    }
}