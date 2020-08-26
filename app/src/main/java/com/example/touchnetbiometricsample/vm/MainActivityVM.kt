package com.example.touchnetbiometricsample.vm

import androidx.biometric.BiometricManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.touchnetbiometricsample.MainRepository
import com.example.touchnetbiometricsample.SingleLiveEvent
import com.example.touchnetbiometricsample.view.base.BaseViewModel
import com.example.touchnetbiometricsample.model.SchoolsDM
import javax.inject.Inject

class MainActivityVM @Inject constructor(private val mainRepository: MainRepository) : BaseViewModel() {
    var errorToast= MutableLiveData<String>()
    var authStatus= MutableLiveData<Boolean>()
    var loginClick= SingleLiveEvent<Void>()
    var showPrompt= SingleLiveEvent<Void>()

    fun onLogin() {
        loginClick.call()
    }

    fun getAllSchools() : LiveData<List<SchoolsDM>>{
        return mainRepository.getAllSchools()
    }

    fun biometricCheck(biometricManager: BiometricManager) {
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                showPrompt.call()

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                errorToast.value = "Device doesn\\'t support biometric features"

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                errorToast.value = "sorry, biometric unavailable!"

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                errorToast.value = "sorry, please setup biometric!"
            }
        }
    }
}