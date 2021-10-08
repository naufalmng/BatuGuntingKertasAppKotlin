package com.example.kbg.fragment.landing_page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.kbg.R
import com.example.kbg.activity.main_menu
import com.example.kbg.databinding.FragmentLandingPage3Binding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


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
        GlideToVectorYou.init()
                .with(this.context)
                .load(Uri.parse(imgUrlSlider),binding.ivLp3)
    }

    }