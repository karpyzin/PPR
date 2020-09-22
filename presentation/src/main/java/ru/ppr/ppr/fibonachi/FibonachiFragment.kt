package ru.ppr.ppr.fibonachi

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_fibonachi.*
import ru.ppr.ppr.R
import ru.ppr.ppr.base.BaseFragment
import ru.ppr.ppr.di.AppInjector
import ru.ppr.ppr.ext.createViewModel
import ru.ppr.ppr.ext.observeValue
import ru.ppr.ppr.main.MainAdapter

class FibonachiFragment : BaseFragment<FibonachiViewModel>() {

    private lateinit var fibonachiAdapter: MainAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun getLayoutId(): Int = R.layout.fragment_fibonachi

    override fun provideViewModel() = createViewModel {
        AppInjector.appComponent.fibonachiViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrimeRv()
        viewModel.dates.observeValue(this) {
            fibonachiAdapter.setDatesList(it.toMutableList())
            view.snack("Выгружен пулл данных")
        }
    }

    private fun initPrimeRv() {
        fibonachiAdapter = MainAdapter{
            viewModel.addFibonachi()
        }
        fibonachiRv.adapter = fibonachiAdapter
        layoutManager = GridLayoutManager(requireContext(), 2)
        fibonachiRv.layoutManager = layoutManager
    }

}