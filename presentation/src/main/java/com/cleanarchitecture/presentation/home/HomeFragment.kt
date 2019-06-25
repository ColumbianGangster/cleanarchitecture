package com.cleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.presentation.common.ErrorViewType
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.extensions.inflate
import com.cleanarchitecture.presentation.navigation.AppNavigator
import com.cleanarchitecture.presentation.search.SearchViewModel
import com.cleanarchitecture.presentation.search.UiSearchNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var navigator: AppNavigator
    private val searchViewModel: SearchViewModel by viewModel()

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(navigator: AppNavigator) = HomeFragment().apply {
            this.navigator = navigator
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_home)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_home_albums.setOnClickListener { navigator.toAlbums() }
    }

    override fun onStart() {
        super.onStart()
        searchViewModel.loadingLiveData.observe(this, Observer {
            loading(it)
        })
        searchViewModel.contentLiveData.observe(this, Observer {
            content(it)
        })
        searchViewModel.errorLiveData.observe(this, Observer {
            error(it)
        })
        searchViewModel.getProducts()
    }

    private fun loading(isLoading: Boolean) {

    }

    private fun content(it: UiSearchNavigation) {
        Log.d("TAG", it.toString())
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
                                searchViewModel.getProducts()
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