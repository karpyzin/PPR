package ru.ppr.ppr.utils

import android.content.Context
import ru.ppr.ppr.R
import ru.ppr.ppr.main.Constants

fun getOrdersTypeNameByOrdersType(context: Context, ordersType: String): String {
    return when (ordersType) {
        Constants.FRAGMENT_PRIME -> context.resources.getString(R.string.type_prime)
        Constants.FRAGMENT_FIBONACHI -> context.resources.getString(R.string.type_finabochi)
        else -> "unknown"
    }
}