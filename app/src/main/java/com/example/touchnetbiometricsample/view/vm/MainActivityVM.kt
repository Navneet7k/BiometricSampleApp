package com.example.touchnetbiometricsample.view.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.touchnetbiometricsample.MainRepository
import com.example.touchnetbiometricsample.SingleLiveEvent
import com.example.touchnetbiometricsample.view.base.BaseViewModel
import com.example.touchnetbiometricsample.view.model.SchoolsDM
import javax.inject.Inject

class MainActivityVM @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {
    var errorToast= MutableLiveData<String>()
    var authStatus= MutableLiveData<Boolean>()
    var loginClick= SingleLiveEvent<Void>()

    fun onLogin() {
        loginClick.call()
    }

    fun getAllSchools() : LiveData<List<SchoolsDM>>{
        return mainRepository.getAllSchools()
    }
}