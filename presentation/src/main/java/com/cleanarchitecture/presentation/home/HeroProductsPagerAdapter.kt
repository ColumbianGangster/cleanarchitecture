package com.cleanarchitecture.presentation.home

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class HeroProductsPagerAdapter(fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = HeroProductFragment.newInstance(position)

    override fun getCount() = 5 //TODO base on API response
}