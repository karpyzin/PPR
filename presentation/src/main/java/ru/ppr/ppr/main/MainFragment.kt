package ru.ppr.ppr.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.ppr.ppr.R
import ru.ppr.ppr.base.BaseFragment
import ru.ppr.ppr.di.AppInjector
import ru.ppr.ppr.ext.createViewModel
import ru.ppr.ppr.fibonachi.FibonachiFragment
import ru.ppr.ppr.prime.PrimeFragment


class MainFragment : BaseFragment<MainViewModel>() {

    private var bottomNav: BottomNavigationView? = null
    private var checkedItem: Int = R.id.navigation_primes

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun provideViewModel() = createViewModel {
        AppInjector.appComponent.mainViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNav = requireActivity().findViewById(R.id.bottom_navigation)
        initNavigation()
        openNavigationPage(PrimeFragment())
    }

    private fun initNavigation() {
        bottomNav!!.setOnNavigationItemSelectedListener {
            val selectedFragment: Fragment = when (it.itemId) {
                R.id.navigation_primes -> PrimeFragment()
                R.id.navigation_fibonachi -> FibonachiFragment()
                else -> PrimeFragment()
            }
            if (it.itemId != checkedItem) {
                checkedItem = it.itemId
                openNavigationPage(selectedFragment)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun openNavigationPage(selectedFragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            selectedFragment
        ).commit()
    }

}