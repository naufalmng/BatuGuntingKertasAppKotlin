package com.example.kbg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.kbg.activity.landing_page
import com.example.kbg.databinding.ActivitySplashScreenBinding

class splash_screen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivitySplashScreenBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)
        handlerSplashScreen()
        animationHook()
       }

    private fun handlerSplashScreen(){
        Handler().postDelayed({
            startActivity(Intent(this, landing_page::class.java))
            finish()
        },1500)
    }

    private fun animationHook(){
        // hook
        binding.bgkLogo.animation = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        binding.bgkLogo2.animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
    }
}