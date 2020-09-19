package ru.ppr.ppr.prime

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_prime.*
import ru.ppr.ppr.R
import ru.ppr.ppr.base.BaseFragment
import ru.ppr.ppr.di.AppInjector
import ru.ppr.ppr.ext.createViewModel
import ru.ppr.ppr.ext.observeValue
import ru.ppr.ppr.main.Constants
import ru.ppr.ppr.main.MainAdapter

class PrimeFragment : BaseFragment<PrimeViewModel>() {

    private lateinit var primeAdapter: MainAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun getLayoutId(): Int = R.layout.fragment_prime

    override fun provideViewModel() = createViewModel {
        AppInjector.appComponent.primeViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrimeRv()
        viewModel.dates.observeValue(this) {
            primeAdapter.setDatesList(it.toMutableList())
            view.snack("Выгружен пулл данных")
        }
    }

    private fun initPrimeRv() {
        primeAdapter = MainAdapter()
        primesRv.adapter = primeAdapter
        layoutManager = GridLayoutManager(requireContext(), 2)
        primesRv.layoutManager = layoutManager
        primesRv.addOnScrollListener(onScrollListener)
    }

    private val onScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val lastVisibleItem = firstVisibleItemPosition + visibleItemCount
                if (totalItemCount - lastVisibleItem <= Constants.PRIME_DELTA) {
                    viewModel.addPrime()
                }
            }
        }
}