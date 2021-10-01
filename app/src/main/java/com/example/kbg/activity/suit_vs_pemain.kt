package com.example.kbg.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kbg.R
import com.example.kbg.databinding.ActivitySuitVsPemainBinding
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit
import io.github.muddz.styleabletoast.StyleableToast

class suit_vs_pemain : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
        open fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }
    private lateinit var p1Move: Suit
    private lateinit var p2Move: Suit
    private var mp = MediaPlayer()

    private val menang = "MENANG!"
    private val seri = "SERI!"
    private val player2 = "Pemain 2"


    private val rock = Rock("rock")
    private val paper = Paper("paper")
    private val scissors = Scissors("scissors")
    private val listElement = arrayOf(rock, paper, scissors)
    private lateinit var binding: ActivitySuitVsPemainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuitVsPemainBinding.inflate(layoutInflater)
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

    private fun setPlayerName() {
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

    private fun actionButtonClicked() {
        binding.batuPlayer.setOnClickListener {
            p1RockButtonTapped()
        }
        binding.kertasPlayer.setOnClickListener {
            p1PaperButtonTapped()
        }
        binding.guntingPlayer.setOnClickListener {
            p1ScissorButtonTapped()
        }
        binding.batuP2.setOnClickListener {
            p2RockButtonTapped()
        }
        binding.kertasP2.setOnClickListener {
            p2PaperButtonTapped()
        }
        binding.guntingP2.setOnClickListener {
            p2ScissorButtonTapped()
        }

    }

    private fun gameSuit() {
        createLog("==========")
        createLog("GAME SUIT")
        createLog("==========")
    }

    private fun startSuit(p1Move: Suit,p2Move: Suit) {

        var result: String? = null
        result = p1Move.actionVersus(p2Move).status
        StyleableToast.makeText(this, "Player 2 Memiilih ${p2Move.element}", Toast.LENGTH_SHORT, R.style.customToastStyle).show()
        createLog("You ${result} ! -> You chose ${p1Move.element}, and computer chose ${p2Move.element}")

        when (result) {
            "win" -> p1Menang()
            "lose" -> p2Menang()
            "draw" -> seri()
        }
    }


    private fun sendSuitStatus(status: String) {
        val intent = intent

        fun playerMenang() {
            val name = intent.getStringExtra("playerNameToActivityVsCom").toString()
            showCustomDialog("p1Menang")
        }

        fun player2Menang() {
            showCustomDialog("p2Menang")
        }

        fun seri() {
            showCustomDialog("seri")
        }

        when (status) {
            "p1Menang" -> playerMenang()
            "p2Menang" -> player2Menang()
            "seri" -> seri()
        }
    }

    private fun p2Menang() {
        sendSuitStatus("p2Menang")
    }

    private fun p1Menang() {
        sendSuitStatus("p1Menang")
    }

    private fun seri() {
        sendSuitStatus("seri")
    }


    // testing
    private fun showCustomDialog(status: String) {

        val playerName = intent.getStringExtra("playerName").toString()
        val view = View.inflate(this, R.layout.fragment_custom_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val customDialog = builder.create()
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val buttonMainLagi = view.findViewById<Button>(R.id.button_main_lagi)
        val buttonKembali = view.findViewById<Button>(R.id.button_kembali)
        val tvDialogPlayerName = view.findViewById<TextView>(R.id.tv_dialog_player_name)
        val tvDialogResultSuit = view.findViewById<TextView>(R.id.tv_dialog_result_suit)

        fun setTextViewCustomDialog() {
            if (status.equals("p1Menang")) {
                tvDialogPlayerName?.text = playerName
                tvDialogResultSuit?.text = menang
            }
            if (status.equals("p2Menang")) {
                tvDialogPlayerName?.text = player2
                tvDialogResultSuit?.text = menang
            }
            if (status.equals("seri")) {
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

//    fun playPaperSound() {
//        mp.release()
//        mp = MediaPlayer.create(this@suit_vs_pemain, R.raw.paper_sound)
//        mp.start()
//    }
//
//    fun playRockSound() {
//        mp.release()
//        mp = MediaPlayer.create(this@suit_vs_pemain, R.raw.rock_sound)
//        mp.start()
//    }
//
//    fun playScissorSound() {
//        mp.release()
//        mp = MediaPlayer.create(this@suit_vs_pemain, R.raw.scissors_sound)
//        mp.start()
//    }

    fun playResetButtonSound() {
        mp.release()
        mp = MediaPlayer.create(this@suit_vs_pemain, R.raw.button_sound)
        mp.start()
    }


    fun resetGame(v: View) {
        playResetButtonSound()
        binding.flBatuPlayer.setBackgroundResource(0)
        binding.flKertasPlayer.setBackgroundResource(0)
        binding.flGuntingPlayer.setBackgroundResource(0)
        binding.flBatuP2.setBackgroundResource(0)
        binding.flKertasP2.setBackgroundResource(0)
        binding.flGuntingP2.setBackgroundResource(0)
        toggleEnableAllButton()
        toggleRevealAllP1Button()

//        tvDialogWinnerName.visibility = View.VISIBLE
    }


    fun p1RockButtonTapped() {
        mulaiSuit()
        p1Move = rock
        createLog("Player 1 choose rock..")
        binding.flBatuPlayer.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP1Button()
        toggleHideAllP1Button()
    }

    fun p1PaperButtonTapped() {
        mulaiSuit()
        p1Move = paper
        createLog("Player 1 choose paper..")
        binding.flKertasPlayer.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP1Button()
        toggleHideAllP1Button()
    }

    fun p1ScissorButtonTapped() {
        mulaiSuit()
        p1Move = scissors
        createLog("Player 1 choose scissors..")
        binding.flGuntingPlayer.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP1Button()
        toggleHideAllP1Button()
    }
    fun p2RockButtonTapped() {
        p2Move = rock
        startSuit(p1Move,p2Move)
        createLog("Player 2 choose rock..")
        binding.flBatuP2.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP2Button()
        toggleRevealAllP1Button()
    }

    fun p2PaperButtonTapped() {
        p2Move = paper
        startSuit(p1Move,p2Move)
        createLog("Player 2 choose paper..")
        binding.flKertasP2.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP2Button()
        toggleRevealAllP1Button()
    }

    fun p2ScissorButtonTapped() {
        p2Move = scissors
        startSuit(p1Move,p2Move)
        createLog("Player 2 choose scissors..")
        binding.flGuntingP2.setBackgroundResource(R.drawable.custom_ripple)
        toggleDisableAllP2Button()
        toggleRevealAllP1Button()
    }

    private fun toggleDisableAllP2Button(){
        binding.kertasP2.isEnabled = false
        binding.batuP2.isEnabled = false
        binding.guntingP2.isEnabled = false
    }
    private fun toggleDisableAllP1Button(){
        binding.kertasPlayer.isEnabled = false
        binding.batuPlayer.isEnabled = false
        binding.guntingPlayer.isEnabled = false
    }

    private fun toggleEnableAllButton(){
        binding.kertasPlayer.isEnabled = true
        binding.guntingPlayer.isEnabled = true
        binding.batuPlayer.isEnabled = true
        binding.kertasP2.isEnabled = true
        binding.guntingP2.isEnabled = true
        binding.batuP2.isEnabled = true
    }

    private fun toggleRevealAllP1Button(){
        binding.flBatuPlayer.visibility = View.VISIBLE
        binding.flKertasPlayer.visibility = View.VISIBLE
        binding.flGuntingPlayer.visibility = View.VISIBLE
        binding.kertasPlayer.visibility = View.VISIBLE
        binding.guntingPlayer.visibility = View.VISIBLE
        binding.batuPlayer.visibility = View.VISIBLE
    }
    private fun toggleHideAllP1Button(){
        binding.flBatuPlayer.visibility = View.INVISIBLE
        binding.flKertasPlayer.visibility = View.INVISIBLE
        binding.flGuntingPlayer.visibility = View.INVISIBLE
        binding.kertasPlayer.visibility = View.INVISIBLE
        binding.guntingPlayer.visibility = View.INVISIBLE
        binding.batuPlayer.visibility = View.INVISIBLE
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}