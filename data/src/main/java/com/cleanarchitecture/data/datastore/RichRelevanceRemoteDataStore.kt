package com.cleanarchitecture.data.datastore


import android.util.Log
import com.cleanarchitecture.data.Commons.Constants
import com.cleanarchitecture.data.api.RichRelevanceApi
import com.cleanarchitecture.data.entities.DataProduct
import com.richrelevance.Callback
import com.richrelevance.RichRelevance
import com.richrelevance.recommendations.Placement
import com.richrelevance.recommendations.PlacementResponseInfo
import io.reactivex.Single


class RichRelevanceRemoteDataStore constructor(private val api: RichRelevanceApi) : RichRelevanceDataStore {

    override fun addToCart(): Single<PlacementResponseInfo> = Single.create { subscriber ->
        // Create a "RecommendationsForPlacements" builder for the "add to cart" placement type.
        val placement = Placement(Placement.PlacementType.ADD_TO_CART, "prod1")
        RichRelevance.buildRecommendationsForPlacements(placement)
                // Attach a callback
                .setCallback(object : Callback<PlacementResponseInfo> {
                    override fun onError(p0: com.richrelevance.Error) {
                        Log.e(javaClass.simpleName, "Error: " + p0.message)
                        subscriber.onError(Exception(p0.message))
                    }

                    override fun onResult(result: PlacementResponseInfo) {
                        val firstPlacement = result.placements[0]
                        val product = firstPlacement.recommendedProducts[0]
                        subscriber.onSuccess(result)
                        product.trackClick()
                    }
                })
                // Execute the request
                .execute()
    }

    override fun getProducts(): Single<List<DataProduct>> =
            api.getProducts(Constants.API_KEY, Constants.API_CLIENT_KEY, Constants.USER_ID,
                    Constants.SESSION_ID, Constants.PLACEMENTS, Constants.PRODUCT_ID)
}

//fun wawdawd() {
//    // Create a "RecommendationsForPlacements" builder for the "add to cart" placement type.
//    val placement = Placement(Placement.PlacementType.ADD_TO_CART, "prod1")
//    RichRelevance.buildRecommendationsForPlacements(placement)
//            // Attach a callback
//            .setCallback(object : Callback<PlacementResponseInfo> {
//                override fun onError(p0: com.richrelevance.Error) {
//                    Log.e(javaClass.simpleName, "Error: " + p0.message)
//                }
//
//                override fun onResult(result: PlacementResponseInfo) {
//                    val firstPlacement = result.placements[0]
//                    val product = firstPlacement.recommendedProducts[0]
//
//                    product.trackClick()
//                }
//            })
//            // Execute the request
//            .execute()
//}
