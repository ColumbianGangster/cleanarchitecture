package com.cleanarchitecture.presentation.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.news_sample_app.databinding.FragmentHomeBinding
import com.cleanarchitecture.presentation.common.ErrorViewType
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.extensions.inflate
import com.cleanarchitecture.presentation.navigation.AppNavigator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var navigator: AppNavigator
    private val homeViewModel: HomeViewModel by viewModel()
    private val promotedItemsAdapter = RichRelevanceHomeAdapter()

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(navigator: AppNavigator) = HomeFragment().apply {
            this.navigator = navigator
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val homeBinding = DataBindingUtil
                .inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        homeBinding.viewModel = homeViewModel
        homeBinding.lifecycleOwner = activity
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home_richrelevance.layoutManager = GridLayoutManager(context, 1,
                GridLayoutManager.HORIZONTAL, false)
        rv_home_richrelevance.adapter = promotedItemsAdapter
        vp_home_hero.adapter = HeroProductsPagerAdapter(fragmentManager
                ?: throw IllegalStateException("Unexpected Error, Please retry again"))

    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getLoadingObservable().observe(this, Observer {
            //loading(it)
        })
        homeViewModel.getContentObservable().observe(this, Observer {
            content(it)
        })
        homeViewModel.getErrorObservable().observe(this, Observer {
            error(it)
        })
        homeViewModel.getPromotedItems()
    }

    private fun loading(isLoading: Boolean) {
        pb_home_richrelevance.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun content(result: List<UiPromotedItem>) {
        promotedItemsAdapter.updateData(result)
    }

    private fun error(error: UiError) {
        activity?.let {
            when (error.errorViewType) {
                ErrorViewType.DIALOG -> {
                    AlertDialog.Builder(it)
                            .setTitle(error.title)
                            .setMessage(error.message)
                            .setPositiveButton(error.positive) { dialog, _ ->
                                dialog.dismiss()
                                homeViewModel.getPromotedItems()
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
}