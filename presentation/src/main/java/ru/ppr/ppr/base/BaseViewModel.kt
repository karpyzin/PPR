package ru.ppr.ppr.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    protected var job: Job = Job()
    protected val ioContext: CoroutineContext get() = Dispatchers.IO + job

    override fun onCleared() {
        disposables.clear()
        job.cancel()
        super.onCleared()
    }
}