package com.example.touchnetbiometricsample.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_FINGERPRINT_ENROLL
import android.provider.Settings.ACTION_SECURITY_SETTINGS
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.touchnetbiometricsample.vm.MainActivityVM
import com.example.touchnetbiometricsample.R
import com.example.touchnetbiometricsample.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityVM: MainActivityVM
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        );
        mainActivityVM= ViewModelProviders.of(this).get(MainActivityVM::class.java)
        binding.viewModel = mainActivityVM

        biometricCheck()
        observeLiveData()
    }

    private fun observeLiveData() {
        mainActivityVM.errorToast.observe(this, Observer {
            Toast.makeText(
                this,it,
                Toast.LENGTH_LONG
            ).show()
        })

        mainActivityVM.authStatus.observe(this, Observer {
            if (it) {
                binding.statusTxt.text = "Login Successful!"
                binding.loginButton.visibility = View.VISIBLE
            }
            else {
                binding.statusTxt.text = "Login Failure!"
                binding.loginButton.visibility = View.GONE
            }
        })

        mainActivityVM.loginClick.observe(this, Observer {
            if (it)
                biometricCheck()
        })
    }

    fun biometricCheck() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricManager = BiometricManager.from(this)

        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                authUser(executor)

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                mainActivityVM.errorToast.value = getString(R.string.biometric_unsupported)

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                mainActivityVM.errorToast.value = getString(R.string.biometric_unavailable)

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                mainActivityVM.errorToast.value = getString(R.string.please_setup_biometric)
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
                    mainActivityVM.authStatus.value = true
                    mainActivityVM.errorToast.value = "Login Successful!"
                }

                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    mainActivityVM.authStatus.value = false
                    mainActivityVM.errorToast.value = "Login Error!"
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    mainActivityVM.authStatus.value = false
                    mainActivityVM.errorToast.value = "Login Error!"
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }
}