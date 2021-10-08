package com.example.kbg.fragment.landing_page

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbg.R
import com.example.kbg.databinding.FragmentLandingPage1Binding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class landing_page1( private val imgUrlSlider: String) :

    Fragment(R.layout.fragment_landing_page1) {
    private lateinit var binding: FragmentLandingPage1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLandingPage1Binding.bind(view)
        setDataImage()
    }


    private fun setDataImage() {
        GlideToVectorYou.init()
            .with(this.context)
            .load(Uri.parse(imgUrlSlider),binding.ivLp1)
    }
}