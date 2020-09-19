package ru.ppr.ppr.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {
    protected var job: Job = Job()
    protected val ioContext: CoroutineContext get() = Dispatchers.IO + job

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}