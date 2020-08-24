package com.example.touchnetbiometricsample.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_FINGERPRINT_ENROLL
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.touchnetbiometricsample.BR
import com.example.touchnetbiometricsample.view.vm.MainActivityVM
import com.example.touchnetbiometricsample.R
import com.example.touchnetbiometricsample.databinding.ActivityMainBinding
import com.example.touchnetbiometricsample.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityVM>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): Class<MainActivityVM> = MainActivityVM::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        biometricCheck()
    }

    fun biometricCheck() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricManager = BiometricManager.from(this)

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                authUser(executor)

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                mViewModel.errorToast.value = getString(R.string.biometric_unsupported)

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                mViewModel.errorToast.value = getString(R.string.biometric_unavailable)

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                mViewModel.errorToast.value = getString(R.string.please_setup_biometric)
                startActivity(Intent(ACTION_FINGERPRINT_ENROLL))
            }
        }
    }

    private fun authUser(executor: Executor) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_login_title))
            .setSubtitle(getString(R.string.biometric_login_subtitle))
            .setDescription(getString(R.string.biometric_login_desc))
            .setDeviceCredentialAllowed(true)
            .build()

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    mViewModel.authStatus.value = true
                    mViewModel.errorToast.value = "Login Successful!"
                }

                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    mViewModel.authStatus.value = false
                    mViewModel.errorToast.value = "Login Error!"
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    mViewModel.authStatus.value = false
                    mViewModel.errorToast.value = "Login Error!"
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }

    override fun subscribeObserver() {
        mViewModel.errorToast.observe(this, Observer {
            Toast.makeText(
                this,it,
                Toast.LENGTH_LONG
            ).show()
        })

        mViewModel.authStatus.observe(this, Observer {
            if (it) {
                status_txt.text = "Login Successful!"
                login_button.visibility = View.GONE
            }
            else {
                status_txt.text = "Login Failure!"
                login_button.visibility = View.VISIBLE
            }
        })

        mViewModel.loginClick.observe(this, Observer {
                biometricCheck()
        })
    }

    override fun unSubscribeObserver() {
        mViewModel.errorToast.removeObservers(this)
        mViewModel.authStatus.removeObservers(this)
        mViewModel.loginClick.removeObservers(this)
    }
}