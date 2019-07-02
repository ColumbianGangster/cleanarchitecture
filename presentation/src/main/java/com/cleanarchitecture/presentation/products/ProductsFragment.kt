package com.cleanarchitecture.presentation.products

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.presentation.common.ErrorViewType
import com.cleanarchitecture.presentation.common.UiError
import com.cleanarchitecture.presentation.common.consume
import com.cleanarchitecture.presentation.common.extensions.inflate
import com.cleanarchitecture.presentation.navigation.AppNavigator
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private lateinit var navigator: AppNavigator
    private val productViewModel: ProductsViewModel by viewModel()

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(navigator: AppNavigator) = ProductsFragment().apply {
            this.navigator = navigator
        }
    }

    private val onItemClick: ((UiProductListing?) -> Unit) = {
        //        it?.let { product ->
//
//        }
    }

    private val onWishItemClicked: ((UiProductListing?) -> Unit) = {
        //        it?.let { product ->
//
//        }
    }

    private lateinit var productAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = container?.inflate(R.layout.fragment_products)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }


    override fun onStart() {
        super.onStart()
        productViewModel.getLoadingObservable().observe(this, Observer {
            loading(it)
        })
        productViewModel.getContentObservable().observe(this, Observer {
            content(it)
        })
        productViewModel.getErrorObservable().observe(this, Observer {
            error(it)
        })
        productViewModel.getProducts()
    }

    private fun initialiseView() {
        productAdapter = ProductsAdapter(
                onItemClick = onItemClick,
                onWishItemClick = onWishItemClicked
        )
        rv_products.layoutManager = GridLayoutManager(this.context, 2)
        rv_products.adapter = productAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.products, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.products_nav_filter -> consume { filter() }
        R.id.products_nav_sort -> consume { sort() }
        else -> super.onOptionsItemSelected(item)
    }

    private fun sort() {

    }

    private fun filter() {

    }

    private fun loading(isLoading: Boolean) {
        pb_products.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun content(it: List<UiProductListing>) {
        it.let { response ->
            productAdapter.updateList(response)
        }
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
                                productViewModel.getProducts()
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
