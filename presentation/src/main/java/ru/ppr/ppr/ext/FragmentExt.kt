package ru.ppr.ppr.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> Fragment.createViewModel(crossinline viewModelProducer: () -> VM): VM {
    val factory = object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <VM : ViewModel> create(modelClass: Class<VM>) = viewModelProducer() as VM
    }
    val viewModelProvider = ViewModelProvider(this, factory)
    return viewModelProvider[VM::class.java]
}