package com.example.kbg.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.kbg.adapter.LandingPageAdapter
import com.example.kbg.databinding.ActivityLandingPageBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.w3c.dom.Text

class landing_page : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityLandingPageBinding.inflate(layoutInflater)
         val view = binding.root
        binding.nextBtn.visibility = View.GONE
        binding.etPlayerName.visibility = View.GONE
        binding.nextBtn.isClickable = false
         setContentView(view)
        initSetAdapterViewPager()
        initSetTabLayoutMediator()
        setOnPageChangeListener()
        toggleDisableEnableNextBtn()
       }

    private fun initActionNextBtn(){
//        binding.etPlayerName.addTextChangedListener(textWatcherValidation)

//        else{
            binding.nextBtn.setOnClickListener {
                val intent = Intent(this,main_menu::class.java)
                val playerName = binding.etPlayerName.text.toString()
                intent.putExtra("playerName",playerName)
                startActivity(intent)
            }

    }

    private fun toggleDisableEnableNextBtn(){
        val name = binding.etPlayerName.text.toString()
        binding.etPlayerName.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s?.length!! > 0){
                    binding.nextBtn.isClickable = true
                    initActionNextBtn()
                }
                else{
                    binding.nextBtn.isClickable = false
                }
            }
        })
    }



    private fun initNextBtnOnClickListener(){
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this,main_menu::class.java)
            val playerName = binding.etPlayerName.text.toString()
            intent.putExtra("playerName",playerName)
            startActivity(intent)
        }
    }


    private fun initSetAdapterViewPager(){
        val adapter = LandingPageAdapter(supportFragmentManager,lifecycle)
        binding.vp2Lp.adapter = adapter
    }

    private fun initSetTabLayoutMediator(){
        val tabLayout = binding.tabLayoutLp
        val viewPager2 = binding.vp2Lp
        TabLayoutMediator(tabLayout,viewPager2){
            tab: TabLayout.Tab, i: Int ->

        }.attach()
    }

    private fun setOnPageChangeListener() {
        val viewPager2 = binding.vp2Lp
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> toggleViewGone()
                    1 -> toggleViewGone()
                    2 -> toggleViewVisible()
                }
            }
        })
    }

    private fun toggleViewGone(){
        val buttonNext = binding.nextBtn
        val editTextView = binding.etPlayerName
        buttonNext.visibility = View.GONE
        editTextView.visibility = View.GONE
    }

    private fun toggleViewVisible(){
        val buttonNext = binding.nextBtn
        val editTextView = binding.etPlayerName
        buttonNext.visibility = View.VISIBLE
        editTextView.visibility = View.VISIBLE


    }
}
