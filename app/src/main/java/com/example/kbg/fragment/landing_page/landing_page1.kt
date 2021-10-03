package com.example.kbg.fragment.landing_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.kbg.R
import com.example.kbg.databinding.FragmentLandingPage1Binding

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
        context?.let {
            Glide.with(it)
                .load(imgUrlSlider)
                .into(binding.ivLp1)

        }
    }
}