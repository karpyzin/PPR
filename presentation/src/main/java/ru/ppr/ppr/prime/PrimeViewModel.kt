package ru.ppr.ppr.prime

import kotlinx.coroutines.*
import ru.ppr.domain.ValuesUseCase
import ru.ppr.ppr.base.BaseViewModel
import ru.ppr.ppr.utils.SingleLiveEvent
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PrimeViewModel @Inject constructor(
    private val valuesUseCase: ValuesUseCase
) : BaseViewModel(), CoroutineScope {

    private var startCount = 2

    val dates = SingleLiveEvent<List<Long>>()

    init {
        loadPrimeAsync()
    }

    fun addPrime() {
        loadPrimeAsync()
    }

    private fun loadPrimeAsync() = async {
        val deferred: Deferred<List<Long>> = async(context = ioContext) {
            valuesUseCase.loadPrime(startCount)
        }

        val list = deferred.await()
        dates.value = list
        startCount = list.last().toInt()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}