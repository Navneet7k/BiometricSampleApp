package com.example.touchnetbiometricsample.view.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getViewModel(): Class<V>

    lateinit var viewDataBinding: T
    lateinit var mViewModel: V

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModel())
        viewDataBinding.setVariable(getBindingVariable(), mViewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
        subscribeObserver()

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    open fun subscribeObserver() {

    }

    open fun unSubscribeObserver() {

    }

    override fun onDestroy() {
        super.onDestroy()
        unSubscribeObserver()
    }
}