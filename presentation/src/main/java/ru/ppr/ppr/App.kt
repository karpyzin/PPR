package ru.ppr.ppr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ppr.ppr.di.AppInjector
import ru.ppr.ppr.di.AppModule
import ru.ppr.ppr.di.DaggerAppComponent
import ru.ppr.ppr.main.MainFragment

class App : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDagger()
        openMainFragment()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
        AppInjector.appComponent = appComponent
    }

    private fun openMainFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.mainFrameLayout,
            MainFragment()
        ).commit()
    }
}