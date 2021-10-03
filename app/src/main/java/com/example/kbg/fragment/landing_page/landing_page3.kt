package com.example.kbg.fragment.landing_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.example.kbg.R
import com.example.kbg.activity.main_menu
import com.example.kbg.databinding.FragmentLandingPage3Binding




class landing_page3(private val imgUrlSlider: String) : Fragment(R.layout.fragment_landing_page3) {
    private lateinit var binding: FragmentLandingPage3Binding
//    private val communicator: ICommunicator = activity as ICommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


//    private val viewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLandingPage3Binding.bind(view)
        setDataImage()

    }
    private fun setDataImage() {
        context?.let {
            Glide.with(it)
                .load(imgUrlSlider)
                .into(binding.ivLp3)

        }
    }

    }