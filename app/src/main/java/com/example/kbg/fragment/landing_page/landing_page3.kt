package com.example.kbg.fragment.landing_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.kbg.R
import com.example.kbg.activity.main_menu
import com.example.kbg.databinding.FragmentLandingPage3Binding


//import com.example.viewmodel.SharedViewModel
//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class landing_page3 : Fragment(R.layout.fragment_landing_page3) {
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
//        passDataToActivity()

    }

//    fun passDataToActivity() {
//        binding.nextBtn.setOnClickListener {
////            val playerName = binding.etPlayerName.text.toString()
//            val intent = Intent(activity, main_menu::class.java)
//            intent.putExtra("player", binding.etPlayerName.text.toString())
//            startActivity(Intent(activity, main_menu::class.java))
//        }


//    private fun passDataToActivity(){
////        val fragment = Fragment()
//
//
//    }
    }