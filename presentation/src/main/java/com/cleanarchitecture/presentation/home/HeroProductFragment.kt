package com.cleanarchitecture.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cleanarchitecture.news_sample_app.R
import com.cleanarchitecture.presentation.common.extensions.inflate
import kotlinx.android.synthetic.main.fragment_hero_product.*

class HeroProductFragment : Fragment() {

    companion object {
        const val TAG = "HeroProductFragment"
        private const val POSITION_KEY = "position"
        fun newInstance(position: Int) = HeroProductFragment().apply {
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = container?.inflate(R.layout.fragment_hero_product)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(POSITION_KEY)
        @DrawableRes val imageId = position?.run {
            return@run when(this) {
                0 -> R.drawable.home_carousel_01
                1 -> R.drawable.home_carousel_02
                2 -> R.drawable.home_carousel_03
                3 -> R.drawable.home_carousel_04
                4 -> R.drawable.home_carousel_05
                else -> R.drawable.hero_placeholder
            }
        }
        context?.let {
            ivHeroImage.setImageDrawable(ContextCompat.getDrawable(it, imageId ?: R.drawable.hero_placeholder))
        }

    }
}