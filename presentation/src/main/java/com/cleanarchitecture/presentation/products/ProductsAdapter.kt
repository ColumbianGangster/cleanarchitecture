package com.cleanarchitecture.presentation.products


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitecture.news_sample_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_products.view.*

class ProductsAdapter(val onItemClick: (UiProductListing?) -> Unit, val onWishItemClick: (UiProductListing?) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    var products = mutableListOf<UiProductListing>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_products, parent, false))

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(productListing: UiProductListing) {
            with(itemView) {

                tvCollectionInActive.visibility = View.GONE
                tvCollectInActiveTick.visibility = View.GONE

                tvDeliveryInActiveTick.visibility = View.GONE
                tvDeliveryInActive.visibility = View.GONE

                tvProductPrice.text = productListing.result.price

                if (productListing.product.badges != null && productListing.product.badges.size >= 1) {
                    tv_products_badge1.text = productListing.product.badges.get(0).name
                } else {
                    tv_products_badge1.visibility = View.GONE
                }

                if (productListing.product.badges != null && productListing.product.badges.size >= 2) {
                    tv_products_badge2.text = productListing.product.badges.get(1).name
                } else {
                    tv_products_badge2.visibility = View.GONE
                }

                if (productListing.product.badges != null && productListing.product.badges.size >= 3) {
                    tv_products_badge3.text = productListing.product.badges.get(2).name
                } else {
                    tv_products_badge3.visibility = View.GONE
                }

                if (productListing.product.wasPrice?.discountAmount?.isNotEmpty() == true) {
                    tvProductSavePrice.text = itemView.context.getString(R.string.products_save, productListing.product.wasPrice?.discountAmount)
                }

                if (productListing.product.deliveryOptions?.isNotEmpty() == true) {
                    if (productListing.product.deliveryOptions?.get(1)?.label.toString() == "Home delivery") {
                        if (productListing.product.deliveryOptions?.get(2)?.enabled.toString() == "false") {
                            tvDeliveryActive.visibility = View.GONE
                            tvDeliveryActiveTick.visibility = View.GONE

                            tvDeliveryInActiveTick.visibility = View.VISIBLE
                            tvDeliveryInActive.visibility = View.VISIBLE
                        }

                    }
                }

                if (productListing.product.deliveryOptions?.isNotEmpty() == true) {
                    if (productListing.product.deliveryOptions?.get(1)?.label.toString() == "Pay & Collect") {
                        if (productListing.product.deliveryOptions?.get(2)?.enabled.toString() == "false") {
                            tvCollectActiveTick.visibility = View.GONE
                            tvDeliveryCollectionActive.visibility = View.GONE

                            tvCollectionInActive.visibility = View.VISIBLE
                            tvCollectInActiveTick.visibility = View.VISIBLE
                        }
                    }
                }

                tvReviewNumbers.text = productListing.result.reevoo_count.toString()
                Picasso.get().load(productListing.result.image).into(ivProduct)
                rtProductRating.rating = productListing.result.reevoo_score.toFloat() / 2
                tvProductDescription.text = productListing.result.title

                setOnClickListener {
                    onItemClick.invoke(productListing)
                }
                iv_products_wish_button.setOnClickListener {
                    onWishItemClick.invoke(productListing)
                }
            }
        }
    }

    fun updateList(list: List<UiProductListing>) {
        if (list.isNotEmpty()) {
            products.clear()
            products.addAll(list)
            notifyDataSetChanged()
        }
    }
}