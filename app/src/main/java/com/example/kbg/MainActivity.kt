package com.example.kbg


import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import com.example.kbg.elements.Paper
import com.example.kbg.elements.Rock
import com.example.kbg.elements.Scissors
import com.example.kbg.suit.Suit
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        val GAME_SUIT = "GAME_SUIT"
        open fun createLog(msg: String) {
            Log.i(GAME_SUIT, msg)
        }
    }

    private lateinit var p1Move: Suit

    //com
    private val rock = Rock("rock")
    private val paper = Paper("paper")
    private val scissors = Scissors("scissors")
    private val listElement = arrayOf(rock, paper, scissors)

    override fun onStart() {
        super.onStart()
        Log.i(GAME_SUIT, "==========")
        Log.i(GAME_SUIT, "GAME SUIT")
        Log.i(GAME_SUIT, "==========")

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        constraintLayout.setOnClickListener {
            Log.i(GAME_SUIT, "==========")
            Log.i(GAME_SUIT, "GAME SUIT")
            Log.i(GAME_SUIT, "==========")
        }
    }


    open fun startSuitWithCom(p1Move: Suit) {

        val random = listElement.random()
        var result = ""
        if (random == listElement.get(0)) {
            createLog("Computer Memilih Batu..")
            result = p1Move.actionVersus(rock).status
            flBatuCom.setBackgroundResource(R.drawable.custom_ripple)
        }
        if (random == listElement.get(1)) {
            createLog("Computer Memilih Kertas..")
            result = p1Move.actionVersus(paper).status
            Log.i(GAME_SUIT, result)
            flKertasCom.setBackgroundResource(R.drawable.custom_ripple)
        }
        if (random == listElement.get(2)) {
            createLog("Computer Memilih Gunting..")
            result = p1Move.actionVersus(scissors).status
            flGuntingCom.setBackgroundResource(R.drawable.custom_ripple)
        }
        when (result) {
            "win" -> playerMenang()
            "lose" -> playerKalah()
            "draw" -> gameDraw()
        }
    }

    private fun gameDraw() {
        vs_id.visibility = View.GONE
        draw.visibility = View.VISIBLE
    }

    private fun playerKalah() {
        vs_id.visibility = View.GONE
        p2_menang.visibility = View.VISIBLE
    }

    private fun playerMenang() {
        vs_id.visibility = View.GONE
        p1_menang.visibility = View.VISIBLE
    }

    private fun mulaiSuit() {
        createLog("MULAI SUIT")
        createLog("Player 1:")
    }

    fun playPaperSound() {
        MediaPlayer.create(baseContext, R.raw.paper_sound).start()
    }

    fun playRockSound() {
        MediaPlayer.create(baseContext, R.raw.rock_sound).start()
    }

    fun playScissorSound() {
        MediaPlayer.create(baseContext, R.raw.scissors_sound).start()
    }
    fun playButtonSound() {
            MediaPlayer.create(baseContext, R.raw.button_sound).start()
    }

    fun resetGame(v: View) {
        playButtonSound()
        flBatuPlayer.setBackgroundResource(0)
        flKertasPlayer.setBackgroundResource(0)
        flGuntingPlayer.setBackgroundResource(0)
        flBatuCom.setBackgroundResource(0)
        flKertasCom.setBackgroundResource(0)
        flGuntingCom.setBackgroundResource(0)
        vs_id.visibility = View.VISIBLE
        p1_menang.visibility = View.GONE
        p2_menang.visibility = View.GONE
        draw.visibility = View.GONE
        kertasPlayer.isEnabled = true
        guntingPlayer.isEnabled = true
        batuPlayer.isEnabled = true
    }


    fun rockButtonTapped(v: View) {
        playRockSound()
        mulaiSuit()
        p1Move = rock
        createLog("Player 1 memilih batu..")
        startSuitWithCom(p1Move)
        flBatuPlayer.setBackgroundResource(R.drawable.custom_ripple)
        kertasPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
        batuPlayer.isEnabled = false
    }

    fun paperButtonTapped(v: View) {
        playPaperSound()
        mulaiSuit()
        p1Move = paper
        createLog("Player 1 memilih kertas..")
        startSuitWithCom(p1Move)
        flKertasPlayer.setBackgroundResource(R.drawable.custom_ripple)
        batuPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
        kertasPlayer.isEnabled = false
    }

    fun scissorButtonTapped(v: View) {
        playScissorSound()
        mulaiSuit()
        p1Move = scissors
        createLog("Player 1 memilih gunting..")
        startSuitWithCom(p1Move)
        flGuntingPlayer.setBackgroundResource(R.drawable.custom_ripple)
        kertasPlayer.isEnabled = false
        batuPlayer.isEnabled = false
        guntingPlayer.isEnabled = false
    }
}