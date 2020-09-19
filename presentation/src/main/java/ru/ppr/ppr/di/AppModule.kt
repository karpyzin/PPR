package ru.ppr.ppr.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.ppr.data.ValuesUseCaseImpl
import ru.ppr.domain.ValuesUseCase
import javax.inject.Singleton

@Module(includes = [AppModule.BindsModule::class])
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext

    @Singleton
    @Provides
    fun providePrefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Module
    abstract inner class BindsModule {

        @Binds
        abstract fun valuesUseCase(arg: ValuesUseCaseImpl): ValuesUseCase

    }
}