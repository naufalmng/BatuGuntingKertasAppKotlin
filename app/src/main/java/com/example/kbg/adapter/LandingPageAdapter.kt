package com.example.kbg.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kbg.fragment.landing_page.landing_page1
import com.example.kbg.fragment.landing_page.landing_page2
import com.example.kbg.fragment.landing_page.landing_page3

class LandingPageAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
       return when(position){
            0 -> landing_page1("https://gist.githubusercontent.com/naufalmng/ac6d0fec95a4a0e3ba3055aa11d5c333/raw/82abb4b6509993262e5822eb462a7c51e68cdf28/landing-page1.svg")
            1 -> landing_page2("https://gist.githubusercontent.com/naufalmng/ac6d0fec95a4a0e3ba3055aa11d5c333/raw/82abb4b6509993262e5822eb462a7c51e68cdf28/landing-page2.svg")
            2 -> landing_page3("https://gist.githubusercontent.com/naufalmng/ac6d0fec95a4a0e3ba3055aa11d5c333/raw/82abb4b6509993262e5822eb462a7c51e68cdf28/landing-page3.svg")
            else -> Fragment()
        }
    }
}