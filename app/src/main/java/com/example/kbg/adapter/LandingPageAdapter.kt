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
            0 -> landing_page1("https://i.ibb.co/x2JT0XQ/landing-page1.png")
            1 -> landing_page2("https://i.ibb.co/RbCSDdn/landing-page2.png")
            2 -> landing_page3("https://i.ibb.co/6Zmp3b4/landing-page3.png")
            else -> Fragment()
        }
    }
}