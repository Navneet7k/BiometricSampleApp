package com.example.touchnetbiometricsample

    import android.app.Activity
    import android.content.Context
    import androidx.multidex.MultiDex
    import androidx.multidex.MultiDexApplication
    import com.example.touchnetbiometricsample.di.AppInjector
    import dagger.android.DispatchingAndroidInjector
    import dagger.android.HasActivityInjector
    import io.github.inflationx.calligraphy3.CalligraphyConfig
    import io.github.inflationx.calligraphy3.CalligraphyInterceptor
    import io.github.inflationx.viewpump.ViewPump
    import javax.inject.Inject


class MvvmApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MvvmApp
        fun appContext(): Context {
            return instance.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        var fontPath : String;
//        if(userSelection == 0) {
//            fontPath = "fonts/FirstFont.ttf"
//        } else if (userSelection == 1) {
//            fontPath = "fonts/SecondFont.ttf"
//        } else {
//            fontPath = "fonts/ThirdFont.ttf"
//        }
        fontPath = "fonts/helveticaneue.ttf"

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(fontPath)
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        );
    }


}
