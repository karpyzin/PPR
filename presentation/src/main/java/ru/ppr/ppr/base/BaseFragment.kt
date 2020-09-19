package ru.ppr.ppr.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import ru.ppr.ppr.di.AppInjector

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected lateinit var viewModel: VM
    protected var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("UNCHECKED_CAST")
        AppInjector.appComponent.inject(this as BaseFragment<BaseViewModel>)

        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    protected fun showDialog(dialog: DialogFragment, tag: String) {
        if (isDetached || childFragmentManager.findFragmentByTag(tag)?.isAdded == true) {
            return
        }

        dialog.show(childFragmentManager, tag)
    }

    protected fun View.snack(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(this, message, duration).show()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun provideViewModel(): VM

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}