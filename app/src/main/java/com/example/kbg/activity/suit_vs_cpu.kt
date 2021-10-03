package com.example.kbg.activity


import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.kbg.R
import com.example.kbg.databinding.ActivitySuitVsCpuBinding
import com.example.kbg.databinding.FragmentCustomDialogBinding
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit
import com.google.android.material.snackbar.Snackbar
import io.github.muddz.styleabletoast.StyleableToast


class suit_vs_cpu : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
         fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }
    private lateinit var p1Move: Suit
    private lateinit var com: Suit
    private var mp = MediaPlayer()

    private val menang = "MENANG!"
    private val seri = "SERI!"
    private val cpu = "CPU"
    private var p1ButtonClicked: Boolean = false
    private var comButtonClicked: Boolean = false
    private var result: String? = null



    private val rock = Rock("rock")
    private val paper = Paper("paper")
    private val scissors = Scissors("scissors")
    private val listElement = arrayOf(rock, paper, scissors)

    private lateinit var binding: ActivitySuitVsCpuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuitVsCpuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        actionButtonClicked()
        setCloseButtonNav()
        setPlayerName()
        binding.rootLayout.setOnClickListener {
            createLog("==========")
            createLog("GAME SUIT")
            createLog("==========")
        }
        }
    private fun setPlayerName(){
        val name = intent.getStringExtra("playerName")
        binding.tvPemain1.text = name
    }
    private fun setCloseButtonNav() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        gameSuit()
    }

    private fun actionButtonClicked(){
        binding.batuPlayer.setOnClickListener {
            rockButtonTapped()
        }
        binding.kertasPlayer.setOnClickListener {
            paperButtonTapped()
        }
        binding.guntingPlayer.setOnClickListener {
            scissorButtonTapped()
        }

    }
    private fun gameSuit(){
        createLog("==========")
        createLog("GAME SUIT")
        createLog("==========")
    }
    private fun checkButtonClicked(comMove: Suit){
        if (p1ButtonClicked == true && comButtonClicked == true){
            suit_vs_pemain.createLog("Player 1 choose ${p1Move.element}")
            suit_vs_pemain.createLog("Com choose ${comMove.element}")
        }
    }
    private fun startSuitWithCom(p1Move: Suit) {


        val random = listElement.random()
        if (random == listElement.get(0)) {
            p1ButtonClicked = true
            comButtonClicked = true
            result = p1Move.actionVersus(rock).status
            binding.flBatuCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Batu",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            checkButtonClicked(rock)
            createLog("You ${result} ! -> You choose ${p1Move.element}, and computer choose ${random.element}")
        }
        if (random == listElement.get(1)) {
            p1ButtonClicked = true
            comButtonClicked = true
            result = p1Move.actionVersus(paper).status
            binding.flKertasCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Kertas",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            checkButtonClicked(paper)
            createLog("You ${result} ! -> You choose ${p1Move.element}, and computer choose ${random.element}")
        }
        if (random == listElement.get(2)) {
            comButtonClicked = true
            result = p1Move.actionVersus(scissors).status
            binding.flGuntingCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Gunting",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            checkButtonClicked(scissors)
            createLog("You ${result} ! -> You choose ${p1Move.element}, and computer choose ${random.element}")
        }
        when (result) {
            "win" -> p1Menang()
            "lose" -> p1Kalah()
            "draw" -> seri()
    }


    }


    private fun sendSuitStatus(status: String){
        val intent = intent

        fun playerMenang(){
            val name = intent.getStringExtra("playerNameToActivityVsCom").toString()
            showCustomDialog("playerMenang")
        }
        fun playerKalah(){
            showCustomDialog("playerKalah")
        }
        fun seri(){
            showCustomDialog("seri")
        }

        when(status){
            "menang" -> playerMenang()
            "kalah" -> playerKalah()
            "seri" -> seri()
        }
    }

    private fun p1Kalah() {
        sendSuitStatus("kalah")
    }

    private fun p1Menang() {
        sendSuitStatus("menang")
    }

    private fun seri() {
        sendSuitStatus("seri")
    }



    // testing
    private fun showCustomDialog(status: String){

        val playerName = intent.getStringExtra("playerName").toString()
//        val view = View.inflate(this,R.layout.fragment_custom_dialog,null)
        val builder = AlertDialog.Builder(this)
        val bind: FragmentCustomDialogBinding = FragmentCustomDialogBinding.inflate(layoutInflater)
        builder.setView(bind.root)
        val customDialog = builder.create()
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)



        fun setTextViewCustomDialog(){
            if(status.equals("playerMenang")){
                bind.tvDialogPlayerName.text =  playerName
                bind.tvDialogResultSuit.text = menang
            }
            if(status.equals("playerKalah")){
                bind.tvDialogPlayerName.text = cpu
                bind.tvDialogResultSuit.text = menang
            }
            if(status.equals("seri")){
                bind.tvDialogPlayerName.visibility = View.INVISIBLE
                bind.tvDialogResultSuit.text = seri
            }
        }

        setTextViewCustomDialog()
        customDialog.setCanceledOnTouchOutside(false)
        customDialog.show()

        bind.buttonMainLagi.setOnClickListener {
            bind.tvDialogPlayerName.visibility = View.VISIBLE
            bind.tvDialogPlayerName.text = "P1"
            customDialog.dismiss()
        }
        bind.buttonKembali.setOnClickListener {
            customDialog.dismiss()
            finish()
        }

    }


    private fun mulaiSuit() {
        createLog("MULAI SUIT..")
    }

    fun playPaperSound() {
        mp.release()
        mp = MediaPlayer.create(this@suit_vs_cpu, R.raw.paper_sound)
        mp.start()
    }

    fun playRockSound() {
        mp.release()
        mp = MediaPlayer.create(this@suit_vs_cpu, R.raw.rock_sound)
        mp.start()
    }

    fun playScissorSound() {
        mp.release()
        mp = MediaPlayer.create(this@suit_vs_cpu, R.raw.scissors_sound)
        mp.start()
    }
    fun playButtonSound() {
        mp.release()
        mp = MediaPlayer.create(this@suit_vs_cpu, R.raw.button_sound)
        mp.start()
    }


    fun resetGame(v: View) {
        val tvDialogWinnerName = findViewById<TextView>(R.id.tv_dialog_player_name)
        playButtonSound()
        binding.flBatuPlayer.setBackgroundResource(0)
        binding.flKertasPlayer.setBackgroundResource(0)
        binding.flGuntingPlayer.setBackgroundResource(0)
        binding.flBatuCom.setBackgroundResource(0)
        binding.flKertasCom.setBackgroundResource(0)
        binding.flGuntingCom.setBackgroundResource(0)
        binding.kertasPlayer.isEnabled = true
        binding.guntingPlayer.isEnabled = true
        binding.batuPlayer.isEnabled = true
        p1ButtonClicked = false
        comButtonClicked = false

//        tvDialogWinnerName.visibility = View.VISIBLE
    }


    fun rockButtonTapped() {
        playRockSound()
        mulaiSuit()
        p1Move = rock
        startSuitWithCom(p1Move)
        binding.flBatuPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.kertasPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
        binding.batuPlayer.isEnabled = false
    }

    fun paperButtonTapped() {
        playPaperSound()
        mulaiSuit()
        p1Move = paper
        startSuitWithCom(p1Move)
        binding.flKertasPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.batuPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
        binding.kertasPlayer.isEnabled = false
    }

    fun scissorButtonTapped() {
        playScissorSound()
        mulaiSuit()
        p1Move = scissors
        startSuitWithCom(p1Move)
        binding.flGuntingPlayer.setBackgroundResource(R.drawable.custom_ripple)
        binding.kertasPlayer.isEnabled = false
        binding.batuPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}