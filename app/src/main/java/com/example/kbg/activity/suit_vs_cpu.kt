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
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit
import com.google.android.material.snackbar.Snackbar
import io.github.muddz.styleabletoast.StyleableToast


class suit_vs_cpu : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
        open fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }
    private lateinit var p1Move: Suit
    var mp = MediaPlayer()

    val menang = "MENANG!"
    val seri = "SERI!"
    val cpu = "CPU"



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

    private fun startSuitWithCom(p1Move: Suit) {

////        fun showToast(int: Int) {
////            when (int){
//                0 ->
//                1 -> StyleableToast.makeText(this,"CPU Memiilih Kertas",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
//                2 -> StyleableToast.makeText(this,"CPU Memiilih Gunting",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
////            }
////
////
//////            fun showToastCpuBatu(){
//////                val toastView = layoutInflater.inflate(R.layout.custom_toast_cpu_batu,
//////                    findViewById(R.id.ll_toast_cpu_batu)
//////                )
//////                with(Toast(applicationContext)){
//////                duration = Toast.LENGTH_SHORT
//////                view = toastView
//////}
//////            }
////        }


        val random = listElement.random()
        var result: String? = null
        if (random == listElement.get(0)) {
            createLog("Computer choose rock..")
            result = p1Move.actionVersus(rock).status
            binding.flBatuCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Batu",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(1)) {
            createLog("Computer choose paper..")
            result = p1Move.actionVersus(paper).status
            binding.flKertasCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Kertas",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
        }
        if (random == listElement.get(2)) {
            createLog("Computer choose scissors..")
            result = p1Move.actionVersus(scissors).status
            binding.flGuntingCom.setBackgroundResource(R.drawable.custom_ripple)
            StyleableToast.makeText(this,"CPU Memiilih Gunting",Toast.LENGTH_SHORT,R.style.customToastStyle).show()
            createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${random.element}")
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
        val view = View.inflate(this,R.layout.fragment_custom_dialog,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val customDialog = builder.create()
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val buttonMainLagi = view.findViewById<Button>(R.id.button_main_lagi)
        val buttonKembali = view.findViewById<Button>(R.id.button_kembali)
        val tvDialogPlayerName = view.findViewById<TextView>(R.id.tv_dialog_player_name)
        val tvDialogResultSuit = view.findViewById<TextView>(R.id.tv_dialog_result_suit)

        fun setTextViewCustomDialog(){
            if(status.equals("playerMenang")){
                tvDialogPlayerName?.text = playerName
                tvDialogResultSuit?.text = menang
            }
            if(status.equals("playerKalah")){
                tvDialogPlayerName?.text = cpu
                tvDialogResultSuit?.text = menang
            }
            if(status.equals("seri")){
                tvDialogPlayerName?.visibility = View.INVISIBLE
                tvDialogResultSuit?.text = seri
            }
        }

        setTextViewCustomDialog()
        customDialog.setCanceledOnTouchOutside(false)
        customDialog.show()

        buttonMainLagi?.setOnClickListener {
            tvDialogPlayerName?.visibility = View.VISIBLE
            tvDialogPlayerName?.text = "P1"
            customDialog.dismiss()
        }
        buttonKembali?.setOnClickListener {
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

//        tvDialogWinnerName.visibility = View.VISIBLE
    }


    fun rockButtonTapped() {
        playRockSound()
        mulaiSuit()
        p1Move = rock
        createLog("Player 1 choose rock..")
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
        createLog("Player 1 choose paper..")
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
        createLog("Player 1 choose scissors..")
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