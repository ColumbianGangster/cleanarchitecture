package com.cleanarchitecture.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.presentation.common.ErrorViewType
import com.cleanarchitecture.presentation.common.FragmentsTransactionsManager
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.consume
import com.cleanarchitecture.presentation.navigation.AppNavigator
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class HomeActivity : AppCompatActivity() {

    private val navigator: AppNavigator by inject { parametersOf(this) }
    private val ftm: FragmentsTransactionsManager by inject { parametersOf(this) }

    private var selectedBottomTabId: Int = -1
    private val defaultSection = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupBottomNav()
    }

    private fun toHome() = goTo(
            fragment = HomeFragment.newInstance(navigator),
            tag = HomeFragment.TAG,
            titleId = R.string.bnav_title_home)

    private fun toSearch() = goTo(
            fragment = HomeFragment.newInstance(navigator),
            tag = HomeFragment.TAG,
            titleId = R.string.bnav_title_search)

    private fun toBasket() = goTo(
            fragment = HomeFragment.newInstance(navigator),
            tag = HomeFragment.TAG,
            titleId = R.string.bnav_title_basket)

    private fun toWishlist() = goTo(
            fragment = HomeFragment.newInstance(navigator),
            tag = HomeFragment.TAG,
            titleId = R.string.bnav_title_wishlist)

    private fun toMore() = goTo(
            fragment = HomeFragment.newInstance(navigator),
            tag = HomeFragment.TAG,
            titleId = R.string.bnav_title_more)

    private fun goTo(fragment: Fragment, tag: String, titleId: Int) {
        setToolbar(titleId)
        ftm.replaceFragment(fragment, tag, R.id.home_container)
    }

    private fun setToolbar(titleId: Int) {
        toolbar_title.apply {
            text = getString(titleId)
        }
    }

    private fun setupBottomNav() {
        bottom_nav.selectedItemId = defaultSection
        selectSection(defaultSection)
        bottom_nav.setOnNavigationItemSelectedListener { selectSection(it.itemId) }
    }

    private fun selectSection(itemId: Int): Boolean = consume {
        if (!isTabSelected(itemId)) {
            when (itemId) {
                R.id.bottom_nav_home -> toHome()
                R.id.bottom_nav_search -> toSearch()
                R.id.bottom_nav_basket -> toBasket()
                R.id.bottom_nav_wishlist -> toWishlist()
                R.id.bottom_nav_more -> toMore()
            }
            selectedBottomTabId = itemId
        }
    }

    private fun isTabSelected(itemId: Int) = selectedBottomTabId > 0 && selectedBottomTabId == itemId

    private fun error(error: UiError) {
        when (error.errorViewType) {
            ErrorViewType.DIALOG -> {
                AlertDialog.Builder(this)
                        .setTitle(error.title)
                        .setMessage(error.message)
                        .setPositiveButton(error.positive) { dialog, _ ->
                            dialog.dismiss()
                            //albumsViewModel.getProducts()
                        }
                        .setCancelable(error.cancelable)
                        .show()
            }
            else -> {
                // NOOP
            }
        }
    }
}
