package ru.ppr.ppr.di

import android.app.Application
import android.content.Context
import dagger.Component
import ru.ppr.ppr.base.BaseFragment
import ru.ppr.ppr.base.BaseViewModel
import ru.ppr.ppr.fibonachi.FibonachiViewModel
import ru.ppr.ppr.main.MainViewModel
import ru.ppr.ppr.prime.PrimeViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(app: Application)

    val context: Context

    fun inject(baseFragment: BaseFragment<BaseViewModel>)

    //ViewModels
    val mainViewModel: MainViewModel
    val primeViewModel: PrimeViewModel
    val fibonachiViewModel: FibonachiViewModel

}