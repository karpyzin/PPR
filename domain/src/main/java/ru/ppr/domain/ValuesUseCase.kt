package ru.ppr.domain

interface ValuesUseCase {
    fun loadPrime(startCount: Int): List<Long>
    fun loadFibonachi(startCount: Int): List<Long>
}