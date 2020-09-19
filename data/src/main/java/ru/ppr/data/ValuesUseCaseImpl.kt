package ru.ppr.data

import ru.ppr.domain.ValuesUseCase
import javax.inject.Inject

class ValuesUseCaseImpl @Inject constructor() : ValuesUseCase {

    private val pullSize = 1000

    override fun loadPrime(startCount: Int): List<Long> = generatePrime(startCount)

    override fun loadFibonachi(startCount: Int): List<Long> = generateFibonachi(startCount)

    private fun generatePrime(startCount: Int): List<Long> {
        val list = mutableListOf<Long>()
        var j: Int
        var i = startCount
        while (i < startCount + pullSize) {
            var k = 0
            j = 2
            while (j <= i) {
                if (i % j == 0) k++
                j++
            }
            if (k < 2) {
                list.add(i.toLong())
            }
            i++
        }
        return list
    }

    private fun generateFibonachi(startCount: Int): List<Long> {
        val list = mutableListOf<Long>()
        var n0 = 1
        var n1 = 1
        var n2: Int
        for (i in startCount..startCount + pullSize) {
            n2 = n0 + n1
            list.add(n2.toLong())
            n0 = n1
            n1 = n2
        }
        return list
    }

}