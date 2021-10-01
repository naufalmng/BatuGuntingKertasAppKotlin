package com.example.kbg.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kbg.databinding.ActivityMainMenuBinding
import kotlin.system.exitProcess

//import com.example.kbg.helper.ICommunicator

class main_menu : AppCompatActivity(){
    private lateinit var binding: ActivityMainMenuBinding
//    private lateinit var p1VsPemain: TextView
//    private lateinit var p1VsCpu: TextView
//    private lateinit var p1NameToast: TextView

//        binding.tvNameToast.text = intent.getStringExtra("playerName")
//        binding.tvP1VsPemain.text = intent.getStringExtra("playerName")
//                .tvP1VsCpu.text = intent.getStringExtra("playerName")
//    }  binding

//    private lateinit var tvP1VsPemain: TextView
//    private lateinit var tvP1VsCpu: TextView

//    override fun onStart() {
//        super.onStart()
//        getAndSetPlayerName()
////        initSetAndGetPlayerName()
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainMenuBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)

        initActionRevealView()
        initActionSlideInUpAnimationView()
        initActionSlideInDownAnimationView()
        initActionImageButtonNav()


       }

    override fun onResume() {
        super.onResume()
        getAndSetPlayerName()
    }


    private fun initActionImageButtonNav() {
        val playerName = intent.getStringExtra("playerName")
        binding.lp2Img.setOnClickListener {
            val intent = Intent(this,suit_vs_cpu::class.java)
            intent.putExtra("playerName",playerName)
//            intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
        binding.lp1Img.setOnClickListener {
            val intent = Intent(this,suit_vs_pemain::class.java)
            intent.putExtra("playerName",playerName)
            startActivity(intent)
        }

    }

//    private fun setImageBtnDirection() {
//        binding.tvP1VsCpu.setOnClickListener {
//            startActivity(Intent(this,suit_vs_cpu::class.java))
//        }
//    }


    private fun initActionSlideInUpAnimationView(){
        YoYo.with(Techniques.SlideInUp)
            .playOn(binding.customView)
    }

    private fun initActionSlideInDownAnimationView(){
        binding.tutupBtn.setOnClickListener {
            YoYo.with(Techniques.SlideInDown)
                .playOn(binding.customView)
                .apply {
                    binding.customView.visibility = View.GONE
                }
        }
    }
    private fun initActionRevealView(){
        binding.customView.visibility = View.VISIBLE
    }

    fun getAndSetPlayerName() {
        val intent = intent
        val playerName = intent.getStringExtra("playerName")
        binding.tvP1VsPemain.text = playerName
        binding.tvP1VsCpu.text = playerName
        binding.tvNameToast.text = playerName
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        exitProcess(-1)
    }

}