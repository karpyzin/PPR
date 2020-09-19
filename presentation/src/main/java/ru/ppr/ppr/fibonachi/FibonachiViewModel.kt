package ru.ppr.ppr.fibonachi

import kotlinx.coroutines.*
import ru.ppr.domain.ValuesUseCase
import ru.ppr.ppr.base.BaseViewModel
import ru.ppr.ppr.utils.SingleLiveEvent
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FibonachiViewModel @Inject constructor(
    private val valuesUseCase: ValuesUseCase
) : BaseViewModel(), CoroutineScope {

    private var startCount = 3

    val dates = SingleLiveEvent<List<Long>>()

    init {
        loadFibonachiAsync()
    }

    fun addFibonachi() {
        loadFibonachiAsync()
    }

    private fun loadFibonachiAsync() = async {
        val deferred: Deferred<List<Long>> = async(context = ioContext) {
            valuesUseCase.loadFibonachi(startCount)
        }

        val list = deferred.await()
        dates.value = list
        startCount = list.last().toInt()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}